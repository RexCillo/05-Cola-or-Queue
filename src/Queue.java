public class Queue implements QueueInterface {

    QueueNode head;
    QueueNode tail;
    int size = 0;
    boolean isPriorityQueue;

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }
    @Override
    public Object extract() {
        if (this.head == null) {
            return null;
        }
        Object data = this.head.object;
        this.head = this.head.next;
        this.size--;
        if (this.head == null) {
            this.tail = null;
        }
        return data;
    }

    @Override
    public boolean insert(Object object) {
        QueueNode nuevo = new QueueNode();
        nuevo.object = object;
        nuevo.priority = 0;
        nuevo.next = null;
        if (this.head == null) {
            this.head = nuevo;
            this.tail = nuevo;
        } else {
            this.tail.next = nuevo;
            this.tail = nuevo;
        }
        this.size++;
        return true;
    }

    @Override
    public boolean insert(Object object, int prioridad) {
        this.isPriorityQueue = true;
        QueueNode nuevo = new QueueNode();
        nuevo.object = object;
        nuevo.priority = prioridad;
        nuevo.next = null;
        if (this.head == null || prioridad > this.head.priority) {
            nuevo.next = this.head;
            this.head = nuevo;
            if (this.tail == null) {
                this.tail = nuevo;
            }
            this.size++;
            return true;
        }
        QueueNode actual = this.head;
        while (actual.next != null && actual.next.priority >= prioridad) {
            actual = actual.next;
        }
        nuevo.next = actual.next;
        actual.next = nuevo;
        if (nuevo.next == null) {
            this.tail = nuevo;
        }
        this.size++;
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean search(Object object) {
        QueueNode actual = this.head;
        while (actual != null) {
            if (object == null) {
                if (actual.object == null) {
                    return true;
                }
            } else {
                if (object.equals(actual.object)) {
                    return true;
                }
            }
            actual = actual.next;
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.head == null) {
            return "[]";
        }
        String resultado = "[";
        QueueNode actual = this.head;
        while (actual != null) {
            resultado = resultado + actual.object;
            if (actual.next != null) {
                resultado = resultado + ", ";
            }
            actual = actual.next;
        }
        resultado = resultado + "]";
        return resultado;
    }
}
