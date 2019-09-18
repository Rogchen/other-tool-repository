title: 适配器模式

description: 说明下


类的适配器模式：当希望将一个类转换成满足另一个新接口的类时，可以使用类的适配器模式，创建一个新类，继承原有的类，实现新的接口即可。

对象的适配器模式：当希望将一个对象转换成满足另一个新接口的对象时，可以创建一个Wrapper类，持有原类的一个实例，在Wrapper类的方法中，调用实例的方法就行。

接口的适配器模式：当不希望实现一个接口中所有的方法时，可以创建一个抽象类Wrapper，实现所有方法，我们写别的类的时候，继承抽象类即可。


一、先模拟计算机读取SD卡：

1、先创建一个SD卡的接口：
```apple js
public interface SDCard {
    //读取SD卡方法
    String readSD();
    //写入SD卡功能
    int writeSD(String msg);
}
```

2、创建SD卡接口的实现类，模拟SD卡的功能：
```apple js
public class SDCardImpl implements SDCard {
    @Override    
    public String readSD() {
        String msg = "sdcard read a msg :hello word SD";
        return msg;  
    }
    @Override  
    public int writeSD(String msg) {      
        System.out.println("sd card write msg : " + msg);    
        return 1;   
    }
}
```

3、创建计算机接口，计算机提供读取SD卡方法:
```apple js
public interface Computer {    
    String readSD(SDCard sdCard);
}
```

4、创建一个计算机实例，实现计算机接口，并实现其读取SD卡方法：
```aspectj
public class ThinkpadComputer implements Computer {
    @Override    
    public String readSD(SDCard sdCard) {        
        if(sdCard == null)throw new NullPointerException("sd card null");        
        return sdCard.readSD();    
    }
}
```

5、这时候就可以模拟计算机读取SD卡功能：
```aspectj
public class ComputerReadDemo {    
    public static void main(String[] args) {        
        Computer computer = new ThinkpadComputer();        
        SDCard sdCard = new SDCardImpl();       
        System.out.println(computer.readSD(sdCard));    
    }
}
```
二、接下来在不改变计算机读取SD卡接口的情况下，通过适配器模式读取TF卡：

1、创建TF卡接口：
```aspectj
public interface TFCard {    
    String readTF();    
    int writeTF(String msg);
}
```

2、创建TF卡实例：
```aspectj
public class TFCardImpl implements TFCard {    
    @Override    
    public String readTF() {        
        String msg ="tf card reade msg : hello word tf card";        
        return msg;    
    }    
    @Override    
    public int writeTF(String msg) {        
        System.out.println("tf card write a msg : " + msg);        
        return 1;    
    }
}
```

3、创建SD适配TF （也可以说是SD兼容TF，相当于读卡器）：

实现SDCard接口，并将要适配的对象作为适配器的属性引入
```aspectj
public class SDAdapterTF implements SDCard {    
    //获取目标实例
    private TFCard tfCard;    
    public SDAdapterTF(TFCard tfCard) {        
        this.tfCard = tfCard;    
    }    
    @Override    
    public String readSD() {        
        System.out.println("adapter read tf card ");        
        return tfCard.readTF();    
    }    
    @Override    
    public int writeSD(String msg) {        
        System.out.println("adapter write tf card");        
        return tfCard.writeTF(msg);    
    }
}
```

4、通过上面的例子测试计算机通过SD读卡器读取TF卡：
```apple js
public class ComputerReadDemo {    
    public static void main(String[] args) {        
        Computer computer = new ThinkpadComputer();        
        SDCard sdCard = new SDCardImpl();        
        System.out.println(computer.readSD(sdCard));        
        System.out.println("====================================");        
        TFCard tfCard = new TFCardImpl();  
        //将目标接口适配到源接口
        SDCard tfCardAdapterSD = new SDAdapterTF(tfCard);        
        System.out.println(computer.readSD(tfCardAdapterSD));    
    }
}
```

5、输出：
```apple js
sdcard read a msg :hello word SD
====================================
adapter read tf card 
tf card reade msg : hello word tf card
```
