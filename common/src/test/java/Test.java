import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class Test {

    public static void main(String[] args) {
        Completable completable1 = Completable.fromAction(() -> {
            System.out.println("Hello!");
        });

        Flowable.range(1,100)
                .map(integer -> {
                    return "" + integer;
                })
                .subscribe(s -> {

                }, throwable -> {

                }, () -> {

                });



        Completable.fromAction(() -> {
            int u = 10/2;
        }).andThen(completable1)
                .subscribe(() -> {
                    System.out.println("Finish!");
                },throwable -> {
                    throwable.printStackTrace();
                });

        int i = Single.just(10)
                .map(integer -> {
                    return integer/0;
                })
                .onErrorReturn(throwable -> {
                    return 2;
                })
                .blockingGet();

        System.out.println(i);
    }
}
