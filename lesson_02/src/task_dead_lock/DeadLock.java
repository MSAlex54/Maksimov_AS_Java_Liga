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

    public synchronized void bow(Friend bower) {
      System.out.format("%s: %s подстрелил меня!\n", this.name, bower.getName());
      System.out.format("%s: стреляю в ответ!\n", this.name);
      bower.bowBack(this);
    }

    /**
     * Решение:
     * В процессе гуглинга наткнулся на слов "реентерабельность". Если я правильно понял,
     * то когда у нас оба метода синхронизированы, то поток получает вторую блокировку объекта в котором вызван метод
     * без проверки заблокирован ли он другим потоком. В итоге каждый из потоков дважды блокирует свой объект
     * и пытается получить второй объект, который также заблокирован дважды.
     */
    public void bowBack(Friend bower) {
      System.out.format("%s: %s подстрелил меня!\n", this.name, bower.getName());
    }
  }

  /**
   * Точка входа в программу
   *
   * @param args аргументы командной строки
   */
  public static void main(String[] args) {
    Friend alphonse = new Friend("Alphonse");
    Friend gaston = new Friend("Gaston");

    new Thread(() -> alphonse.bow(gaston)).start();
    new Thread(() -> gaston.bow(alphonse)).start();
  }
}

