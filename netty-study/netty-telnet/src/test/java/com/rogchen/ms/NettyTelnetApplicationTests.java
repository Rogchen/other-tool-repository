package com.rogchen.ms;

import com.rogchen.ms.telnet.NettyTelnetServer;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class NettyTelnetApplicationTests {

	@Test
	public void contextLoads() {
		NettyTelnetServer nettyTelnetServer = new NettyTelnetServer();
		try {
			nettyTelnetServer.openTelnet();
		} catch (InterruptedException e) {
			nettyTelnetServer.close();
		}
	}

}

