package lessons03;

public class MyLinkedList {

    private Node head; // Ссылка на первый элемент

    public Node getHead() {
        return head;
    }

    public void addFirst(int value){
        Node node = new Node(value);
        if (head != null)
            node.setNext(head);
        head = node;
    }

    public void removeFirst(){
        if (head != null)
            head = head.getNext();
    }


    public Node contains(int value){
        Node node = head;
        while (node != null){
            if (node.getValue() == value)
                return node;
            node = node.getNext();
        }
        return null;
    }

    public void addLast(int value){
        Node node = new Node(value);
        if (head == null){
            head = node;
        }
        else {
            Node last = head;
            while (last.getNext() != null){
                last = last.getNext();
            }
            last.setNext(node);
        }
    }

    public void removeLast(){
        if (head == null)
            return;

        Node node = head;

        while (node.getNext() != null){
            if (node.getNext().getNext() == null)
            {
                node.setNext(null);
                return;
            }
            node = node.getNext();
        }

        head = null;

    }

    public void revert(){
        if(head != null && head.getNext() != null){
            Node buf = head;
            revert(head.getNext(), head);
            buf.setNext(null);
        }
    }
    private void revert(Node currentNode, Node previousNode){
        if(currentNode.getNext() != null){
            revert(currentNode.getNext(), currentNode);
        } else {
            head = currentNode;
        }
        currentNode.setNext(previousNode);
    }

}