package com.algorithm.chord.service;

import com.algorithm.chord.Main;
import com.algorithm.chord.pojo.ChordNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ChordNodeService {

    private List<ChordNode> nodeList;

    private static ChordNodeService instance;

    private ChordNodeService() {
        this.nodeList = new ArrayList<>();
    }

    public static ChordNodeService getInstance() {
        if (instance == null) instance = new ChordNodeService();
        return instance;
    }

    public ChordNode findNodeById() {
        return null;
    }

    public void addNode(ChordNode node) {
        if (nodeList.size() == 1) {
            nodeList.get(0).setSuccessor(node);
            nodeList.get(0).setPredecessor(node);
            node.setSuccessor(nodeList.get(0));
            node.setPredecessor(nodeList.get(0));
        } else {
            ChordNode nextNode = null;
            ChordNode prevNode = null;

            // define next node
            for (ChordNode cN : nodeList) {
                if (cN.getId() > node.getId()) {
                    nextNode = cN;
                }
            }

            if (nextNode == null) {
                nextNode = getFirstNode();
            }
            if (nextNode != null) {
                node.setSuccessor(nextNode);
                nextNode.setPredecessor(node);
            }

            // define prev node
            for (ChordNode cN : nodeList) {
                if (cN.getId() < node.getId()) {
                    prevNode = cN;
                }
            }

            if (prevNode == null) {
                prevNode = getLastNode();
            }
            if (prevNode != null) {
                node.setPredecessor(prevNode);
                prevNode.setSuccessor(node);
            }

            Map<Integer, ChordNode> nodePredFingerTable = node.getPredecessor().getFingerTable();
            for (int fTK : nodePredFingerTable.keySet()) {
                if (fTK <= node.getId()) {
                    nodePredFingerTable.put(fTK, node);
                }
            }
        }

        nodeList.add(node);
        sortNodeList();

        for (int i = 1; i <= Main.M_intM; i++) {
            int start = (int) Math.round((node.getId() + Math.pow(2, i - 1)) % (Math.pow(2, Main.M_intM)));

            ChordNode tmp = null;
            for (ChordNode cN : nodeList) {
                if (cN.getId() >= start) {
                    tmp = cN;
                }
            }

            if (tmp == null) {
                tmp = nodeList.get(0);
            }

            node.getFingerTable().put(start, tmp);
        }
    }

    public void removeNode(int id) {
        ChordNode node = null;

        for (ChordNode cN : nodeList) {
            if (cN.getId() == id) {
                node = cN;
            }
        }

        if (node != null) {
            node.getSuccessor().setPredecessor(node.getPredecessor());
            node.getPredecessor().setSuccessor(node.getSuccessor());
            nodeList.remove(node);

            for (int i = 1; i <= Main.M_intM; i++) {
                int start = (int) Math.round((node.getPredecessor().getId() + Math.pow(2, i - 1)) % (Math.pow(2, Main.M_intM)));

                ChordNode tmp = null;
                for (ChordNode cN : nodeList) {
                    if (cN.getId() >= start) {
                        tmp = cN;
                    }
                }

                if (tmp == null) {
                    tmp = nodeList.get(0);
                }

                node.getPredecessor().getFingerTable().put(start, tmp);
            }
        }
    }


    public void printNodeList() {
        for (ChordNode chordNode : nodeList) {
            System.out.println("\n-----------------------------------");
            System.out.println(chordNode);
            System.out.println("-----------------------------------");
        }
    }

    private void sortNodeList() {
        nodeList.sort(Comparator.comparing(ChordNode::getId));
    }

    private ChordNode getFirstNode() {
        return nodeList.size() > 0 ? nodeList.get(0) : null;
    }

    private ChordNode getLastNode() {
        return nodeList.size() > 0 ? nodeList.get(nodeList.size() - 1) : null;
    }
}
