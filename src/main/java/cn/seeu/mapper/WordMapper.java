package cn.seeu.mapper;

import cn.seeu.entry.Word;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WordMapper {
    public Word getWordById(Integer id);

    public int addWord(Word word);

    public void updateWord(Word word);

    public void deleteWord(@Param("id") Integer id);

    public List<Word> getWordsByIsDelete(Integer status);

    // 返回一条记录的map
    public Map<String, Object> getWordByIdReturnMap(Integer id);

    // 多条记录封装在map
    @MapKey("id")
    public Map<Integer, Word> getWordsByIsDeleteReturnMap(Integer status);
}
