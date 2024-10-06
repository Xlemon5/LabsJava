
public class Main {
    public static void main(String[] args) {
        container<Integer> container = new container<>();
        container.add(10);
        container.add(20);
        container.add(30);

        System.out.println("Размер контейнера: " + container.size());
        container.printElems();
        System.out.println("Элемент по индексу 1: " + container.get(1));

        System.out.println("--------------------------");
        container.remove(1);
        container.printElems();
        System.out.println("Размер контейнера после удаления: " + container.size());
        System.out.println("Элемент по индексу 1 после удаления: " + container.get(1));
    }
}
