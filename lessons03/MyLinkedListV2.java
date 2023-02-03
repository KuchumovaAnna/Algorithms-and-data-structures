package lessons03;

public class MyLinkedListV2 {

    private NodeV2 head;
    private NodeV2 tail;

    public NodeV2 getHead() {
        return head;
    }

    public NodeV2 getTail() {
        return tail;
    }

    public void addFirst(int value){
        NodeV2 node = new NodeV2(value);
        if (head != null){
            node.setNext(head);
            head.setPrev(node);
            //head = node;
        }
        else {
            //head = node;
            tail = node;
        }

        head = node;
    }

    public void addLast(int value){
        NodeV2 node = new NodeV2(value);
        if (head == null){
            head = node;
        }
        else {
            NodeV2 last = head;
            while (last.getNext() != null){
                last = last.getNext();
            }
            last.setNext(node);
        }
    }

    public void removeFirst(int value){
        if (head != null && head.getNext() != null){
            head.getNext().setPrev(null);
            head = head.getNext();
        }
        else {
            head = null;
            tail = null;
        }


    }

    public void revert(){
        if(head != null && head.getNext() != null){
            NodeV2 bufHead = head;
            revert(head.getNext(), head);
            bufHead.setNext(null);
            tail = bufHead;
        }

    }
    private void revert(NodeV2 currentNode, NodeV2 previousNode){
        if(currentNode.getNext() != null){
            revert(currentNode.getNext(), currentNode);
        } else {
            head = currentNode;
        }
        currentNode.setNext(previousNode);
        previousNode.setPrev(currentNode);
    }

}