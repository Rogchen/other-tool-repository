package com.ylzinfo.ms.im.xss;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 针对所有请求都做拦截。
 * @Author Rogchen rogchen128@gmail.com
 * @Created 2019/9/12 13:52
 **/
public class XssFilter implements Filter {


    /**
     * 是否过滤富文本内容
     */
    private static boolean IS_INCLUDE_RICH_TEXT = false;

    public List<String> excludes = new ArrayList<>();
    public List<String> includes = new ArrayList<>();

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (handlURL(req, resp)) {
            chain.doFilter(request, response);
            return;
        }
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    /**
     * 规则说明：
     * 包含跟不包含相反，默认进入过滤
     *
     * @param request
     * @param response
     * @return
     */
    private boolean handlURL(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getServletPath();
//        包含
        if (includes != null && !includes.isEmpty()) {
            for (String pattern : includes) {
                //匹配到了 最终返回false 进入过滤
                if (matcherPath(url, pattern)) {
                    return false;
                }
            }
        }
        if (excludes == null || excludes.isEmpty()) {
            return false;
        }
        for (String pattern : excludes) {
            if (matcherPath(url, pattern)) {
                return true;
            }
        }
        return false;
    }

    private boolean matcherPath(String url, String path) {
        String[] ps = path.split("-");
        switch (ps[0]) {
            case "0":
                if (ps[1].equals(url)) {
                    return true;
                }
                return false;
            case "1":
                if (url.endsWith(ps[1])) {
                    return true;
                }
                return false;
            case "2":
                if (url.startsWith(ps[1])) {
                    return true;
                }
                return false;
        }
        return false;
    }


    private boolean startMatcherPath(String path) {
        long count = includes.parallelStream().mapToInt(m -> {
            if (path.startsWith(m)) {
                return 1;
            }
            return 0;
        }).count();
        if (count > 1) {
            return true;
        }
        return false;
    }


    /**
     * 全词匹配：0 全路径匹配，模糊匹配： 1 开头匹配，2 结尾匹配
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excs = filterConfig.getInitParameter("excludes");
        String indes = filterConfig.getInitParameter("includes");
        if (StringUtils.isNoneBlank(excs)) {
            openPath(excs, false);
        }
        if (StringUtils.isNoneBlank(indes)) {
            openPath(indes, true);
        }

    }

    private void openPath(String temp, boolean flag) {
        List<String> allList = new ArrayList<>();
        if (temp != null) {
            String[] ils = temp.split(",");
            for (int i = 0; ils != null && i < ils.length; i++) {
                //模糊匹配的
                // 所有*开头的匹配
                if (ils[i].startsWith("/*")) {
                    allList.add("1-" + ils[i].replace("/*", ""));
                } else if (ils[i].endsWith("/*")) {
                    // 所有*结尾的匹配
                    allList.add("2-" + ils[i].replace("/*", ""));
                } else {
                    allList.add("0-" + ils[i]);
                }
            }
        }
        if (flag) {
            includes.addAll(allList);
        } else {
            excludes.addAll(allList);
        }
    }

}