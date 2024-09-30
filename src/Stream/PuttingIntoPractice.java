package Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> transactionsWithYear = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .toList();
        System.out.println("Транзакции за 2011 год отсортированные по сумме: " + transactionsWithYear);

        List<String> citys = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .toList();
        System.out.println("Список уникальных городов, в которых работают трейдеры: " + citys);

        List<String> allTradersList = transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted(Comparator.naturalOrder())
                .distinct()
                .toList();
        System.out.println("Список всех трейдеров: " + allTradersList);

        boolean tradersMilan = transactions.stream()
                .anyMatch(t -> Objects.equals(t.getTrader().getCity(), "Milan"));

        System.out.println("Есть ли трейдер в Милане?\nОтвет: " + tradersMilan);

        List<Integer> sumTransactionsFromCambridge = transactions.stream()
                .filter(t -> Objects.equals(t.getTrader().getCity(), "Cambridge"))
                .map(Transaction::getValue)
                .toList();
        System.out.println("Список сумм транзакций трейдеров из Кембриджа: " + sumTransactionsFromCambridge);

        List<String> tradersListFromCambridge = transactions.stream()
                .filter(t -> Objects.equals(t.getTrader().getCity(), "Cambridge"))
                .map(t -> t.getTrader().getName())
                .sorted(Comparator.naturalOrder())
                .distinct()
                .toList();
        System.out.println("Список трейдеров из Кембриджа: " + tradersListFromCambridge);

        int minSumm = transactions.stream()
                .map(Transaction::getValue)
                .min(Integer::compare)
                .get();
        System.out.println("Максимальная сумма: " + minSumm);

        int maxSumm = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare)
                .get();
        System.out.println("Максимальная сумма: " + maxSumm);
    }
}