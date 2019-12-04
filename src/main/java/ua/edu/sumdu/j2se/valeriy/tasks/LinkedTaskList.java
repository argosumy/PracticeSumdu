package ua.edu.sumdu.j2se.valeriy.tasks;



public class LinkedTaskList extends AbstractTaskList {
    private int count;
    private Node actualNode;
    //private Node firstNode;
    //private LinkedList arrayListNew;
    //private Node firstNode;

    /*
     * construction
     * */
    public LinkedTaskList() {
        count = 0;
        actualNode = new Node(null);
    }

    public Node getActualNode() {
        return actualNode;    }

    public LinkedTaskList(Node actualNode) {
        this.actualNode = actualNode;    }

    public void setActualNode(Node actualNode) {
        this.actualNode = actualNode;    }
    @Override
    public void add(Task task)throws NullPointerException{
        Node newNode = new Node(task);
        Node actualNode1 = actualNode;

        while (actualNode1.getNextNode() != null){
            actualNode1 = actualNode1.getNextNode();
        }
        newNode.setNextNode(actualNode1.getNextNode());
        actualNode1.setNextNode(newNode);
        count++;
    }
    @Override
    public boolean remove(Task task) {
        if (actualNode == null) {
            return false;
        }
        Node actualNode1 = actualNode;
        int i = 0;
        while (actualNode1.getNextNode().getNextNode() != null) {
            if (actualNode1.getNextNode().getTask() == task) {
                if (i == 0) {
                    actualNode.setNextNode(actualNode1.getNextNode().getNextNode());
                    count--;
                    return true;
                }
                if (actualNode1.getNextNode().getNextNode() != null) {
                    actualNode1.setNextNode(actualNode1.getNextNode().getNextNode());
                    count--;
                    return true;
                }
            }
            if ((actualNode1.getNextNode().getNextNode().getNextNode() == null) &
                    (actualNode1.getNextNode().getNextNode().getTask() == task)){
                actualNode1.getNextNode().setNextNode(null);
                count--;
                return true;
            }
            actualNode1 = actualNode1.getNextNode();
            i++;
        }
        return false;
    }
    @Override
    public int size() {
        return count;
    }
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException
    {
        if (index < 0) {
            return null;
        }
        Node current = null;
        if (actualNode != null) {
            current = actualNode.getNextNode();
            for (int i = 0; i < index; i++) {
                if (current.getNextNode() == null) {
                    return null;
                }
                current = current.getNextNode();
            }
            return current.getTask();
        }
        throw new IndexOutOfBoundsException("Index out of Bounds");
    }
    @Override
    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList linkedTaskList = new LinkedTaskList();

        Node current = actualNode;
        while(current.getNextNode() != null)  {
            current = current.getNextNode();
            for (int i = from; i < to; i++) {
                if(!current.getTask().isActive() || current.getTask() == null){
                    break;
                }
                if((current.getTask().nextTimeAfter(i) != -1) && (current.getTask().nextTimeAfter(i) < to)) {
                    linkedTaskList.add(current.getTask());
                    break;
                }
            }
        }
        return linkedTaskList;
    }







}

