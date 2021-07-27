package cn.seeu.entry;

import org.apache.ibatis.type.Alias;

public class Word {
    private int id;
    private String word;
    private String updatedAt;
    private String isDelete;

    public Word() {
        super();
    }

    public Word(String word, String updatedAt) {
        this.word = word;
        this.updatedAt = updatedAt;
    }

    public Word(int id, String word, String updatedAt) {
        this.id = id;
        this.word = word;
        this.updatedAt = updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
