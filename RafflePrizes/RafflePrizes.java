
package RafflePrizes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.logging.Logger;

public class RafflePrizes {
    Prize[] prizeList = new Prize[4];
    PriorityQueue<Prize> prizeQueue = new PriorityQueue<>();
    private static final int QUANTITY_PRIZE = 10;
    Random random = new Random();

    public static void main(String[] args) {
        RafflePrizes RafflePrizes1 = new RafflePrizes();
        RafflePrizes1.generatePrizeList();
    }
    public void generatePrizeList() {
        Logger logger = Logger.getLogger("main");
        RafflePrizes RafflePrizes1 = new RafflePrizes();
        RafflePrizes1.initPrizes(null);
        logger.info("list of toys: " + Arrays.toString(RafflePrizes1.prizeList));
        try {
            FileWriter writer = new FileWriter("result.txt");
            for (int i = 0; i < QUANTITY_PRIZE; i++) {
                writer.write(RafflePrizes1.getRandomPrize().getId() + "\n");
            }
            writer.close();
            System.out.println("Prize generation is complete.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // logger.info("Current queue: " + RafflePrizes1.prizeQueue);
        logger.info("Number of prizes in queue: " + RafflePrizes1.prizeQueue.size());
    }
    public void initPrizes(Prize[] prizes) {
        if (prizes == null) {
            generatePrizes();
        } else {
            prizeList = null;
            prizeList = new Prize[prizes.length];
            for (int i = 0; i < prizes.length; i++) {
                Prize p = prizes[i];
                prizeList[i] = new Prize(p.getId(), p.getName(), p.distributionWeight);
            }
        }
    }
    private void generatePrizes() {
        for (int i = 0; i < 4; i++) {
            int index = i + 1;
            prizeList[i] = new Prize(index, "toy"+index, index * 10);
        }
    }
    public void initQueue() {
        Logger logger = Logger.getLogger("initQueue");
        logger.info("queue initialization...");
        for(Prize item: prizeList) {
            for(int i = 0; i< item.distributionWeight; i++) {
                prizeQueue.offer(item);
            }
        }
    }
    private boolean getPrize(Prize prize) {
        return prizeQueue.remove(prize);
    }
    public Prize getRandomPrize() {
        if (prizeQueue.size() < 1) {
            initQueue();
        }
        int prizeType = 0;
        boolean isPrizeGot = false;
        while (!isPrizeGot) {
            prizeType = random.nextInt(prizeList.length);
            isPrizeGot = getPrize(prizeList[prizeType]);
        };
        return prizeList[prizeType];
    }
}
