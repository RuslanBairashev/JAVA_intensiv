import java.util.LinkedList;
import java.util.UUID;

public class TransactionLinkedList implements TransactionsList {

    private Transaction first;
    private Transaction last;
    private int size = 0;

    public TransactionLinkedList() {
    }

    static public class TransactionNotFoundException extends RuntimeException {

    }
    @Override
    public void addTransaction(Transaction transaction) {
        if (first == null && last == null) {
            first = transaction;
            last = transaction;
            size++;
            return;
        }
        transaction.setPrev(last);
        this.last.setNext(transaction);
        this.last = transaction;
        size++;
    }

    @Override
    public void removeTransactionById(UUID id) {
        Transaction head = first;
        Transaction tmp = null;
        for (int i = 0; i < size; i++) {
            if (head.getIdentifier() == id) {
                tmp = head;
                break;
            }
            head = head.getNext();
        }
        if(tmp == null){
            throw new TransactionLinkedList.TransactionNotFoundException();
        }

        Transaction tmpPrev = tmp.getPrev();
        Transaction tmpNext = tmp.getNext();

        if (tmpPrev == null && tmpNext == null) {
            this.first = null;
            this.last = null;
        } else if (tmpPrev == null) {
            this.first = tmpNext;
            if (tmpNext != null) {
                tmpNext.setPrev(null);
            }
        } else if (tmpNext == null) {
            this.last = tmpPrev;
            if (tmpPrev != null) {
                tmpPrev.setNext(null);
            }
        } else {
            tmpNext.setPrev(tmpPrev);
            tmpPrev.setNext(tmpNext);
        }
        size--;
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transactionsList = new Transaction[size];
        Transaction head = first;
        for (int i = 0; i < size; i++) {
            transactionsList[i] = head;
            head = transactionsList[i].getNext();
        }
        return transactionsList;
    }

    public int getSize() {
        return size;
    }
}
