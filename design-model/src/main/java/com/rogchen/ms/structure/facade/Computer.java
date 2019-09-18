package com.rogchen.ms.structure.facade;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/6 17:01
 **/
public class Computer {

    private Cpu cpu;
    private Memony memony;
    private Disk disk;

    public Computer(){
        cpu = new Cpu();
        memony = new Memony();
        disk = new Disk();
    }

    public void startUp(){
        System.out.println("computer is begining start");
        cpu.startup();
        memony.startup();
        disk.startup();
    }

    public static void main(String[] args) {
        Computer cd = new Computer();
        cd.startUp();
    }
}
