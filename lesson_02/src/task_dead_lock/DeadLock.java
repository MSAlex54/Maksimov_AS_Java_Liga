package task_dead_lock;

public class DeadLock {

    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void bow(Friend bower) {
            synchronized (this) {
                System.out.format("%s: %s ���������� ����!\n", this.name, bower.getName());
                System.out.format("%s: ������� � �����!\n", this.name);
            }
            bower.bowBack(this);
        }

        /**
         * �������:
         * � �������� �������� ��������� �� ���� "�����������������". ���� � ��������� �����,
         * �� ����� � ��� ��� ������ ����������������, �� ����� �������� ������ ���������� ������� � ������� ������ �����
         * ��� �������� ������������ �� �� ������ �������. � ����� ������ �� ������� ������ ��������� ���� ������
         * � �������� �������� ������ ������, ������� ����� ������������ ������.
         */
        public void bowBack(Friend bower) {
            synchronized (this) {
                System.out.format("%s: %s ���������� ����!\n", this.name, bower.getName());
            }
        }
    }

    /**
     * ����� ����� � ���������
     *
     * @param args ��������� ��������� ������
     */
    public static void main(String[] args) {
        Friend alphonse = new Friend("Alphonse");
        Friend gaston = new Friend("Gaston");

        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();
    }
}

