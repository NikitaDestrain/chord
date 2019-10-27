package com.algorithm.chord.pojo;

import com.algorithm.chord.Main;

import java.util.HashMap;

public class ChordNode {
    private int id;
    private ChordNode predecessor;
    private ChordNode successor;
    private HashMap<Integer, ChordNode> fingerTable;

    public ChordNode(int id) {
        this.id = id;
        this.predecessor = this;
        this.successor = this;
        initFingerTable(id);
    }

    private void initFingerTable(int id) {
        fingerTable = new HashMap<>();
        for (int i = 1; i <= Main.M_intM; i++) {
            int key = (int) Math.round((id + Math.pow(2, (i - 1))) % (Math.pow(2, Main.M_intM)));
            fingerTable.put(key, null);
        }
    }

    public ChordNode getSuccessor() {
        int firstKey = (int) fingerTable.keySet().toArray()[0];
        return fingerTable.get(firstKey);
    }

    public void setSuccessor(ChordNode value) {
        int firstKey = (int) fingerTable.keySet().toArray()[0];
        fingerTable.put(firstKey, value);
        this.successor = value;
    }

    public int getId() {
        return id;
    }

    public ChordNode getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(ChordNode predecessor) {
        this.predecessor = predecessor;
    }

    public HashMap<Integer, ChordNode> getFingerTable() {
        return fingerTable;
    }

    @Override
    public String toString() {
        return "ChordNode{" +
                "id=" + id +
                ", predecessor=" + predecessor.getId() +
                ", successor=" + successor.getId() +
                ", fingerTable key set=" + fingerTable.keySet() +
                '}';
    }
}