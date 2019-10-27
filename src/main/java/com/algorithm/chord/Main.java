package com.algorithm.chord;

import com.algorithm.chord.pojo.ChordNode;
import com.algorithm.chord.service.ChordNodeService;

public class Main {
    public static final int M_intM = 3;
    private static final int[] m_arPos = {0, 1, 3};

    public static void main(String[] args) {
        System.out.println("----------------------CHORD------------------------");
        System.out.println("M_intM: " + M_intM);

        ChordNodeService cNS = ChordNodeService.getInstance();
        for (int i = 0; i < m_arPos.length; i++) {
            int pos = m_arPos[i];
            System.out.println("[INFO]: Init node at " + pos + " position");
            cNS.addNode(new ChordNode(pos));
            System.out.println("[INFO]: Success");
        }

        cNS.printNodeList();
        cNS.removeNode(3);
        System.out.println("--aft remove");
        cNS.printNodeList();
    }
}
