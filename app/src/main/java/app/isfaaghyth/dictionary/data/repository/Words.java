package app.isfaaghyth.dictionary.data.repository;

/**
 * Created by isfaaghyth on 7/30/18.
 * github: @isfaaghyth
 */

public class Words {

    private int id;
    private String words;
    private String means;

    public int getId() {
        return id;
    }

    public String getWords() {
        return words;
    }

    public String getMeans() {
        return means;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setWords(String words) {
        this.words = words;
    }

    private void setMeans(String means) {
        this.means = means;
    }

    public static class Builder {

        private Words words;

        public Builder() {
            words = new Words();
        }

        public Builder setId(int id) {
            words.setId(id);
            return this;
        }

        public Builder setWords(String word) {
            words.setWords(word);
            return this;
        }

        public Builder setMeans(String means) {
            words.setMeans(means);
            return this;
        }

    }
}
