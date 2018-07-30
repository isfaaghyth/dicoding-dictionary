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

    private Words(Builder builder) {
        id = builder.id;
        words = builder.words;
        means = builder.means;
    }

    public static final class Builder {
        private int id;
        private String words;
        private String means;

        public Builder() {
        }

        public Builder setId(int val) {
            id = val;
            return this;
        }

        public Builder setWords(String val) {
            words = val;
            return this;
        }

        public Builder setMeans(String val) {
            means = val;
            return this;
        }

        public Words build() {
            return new Words(this);
        }
    }

}
