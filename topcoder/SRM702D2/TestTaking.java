public class TestTaking {
    public int findMax(int questions, int guessed, int actual) {
        return Math.min(guessed, actual)
            + Math.min(questions - guessed, questions - actual);
    }
}
