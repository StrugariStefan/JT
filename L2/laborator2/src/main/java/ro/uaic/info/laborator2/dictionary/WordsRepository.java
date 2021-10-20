/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uaic.info.laborator2.dictionary;

import java.util.List;

/**
 *
 * @author sstrugar
 */
public interface WordsRepository {
    public List<Word> Get();
    
    public void Create(Word word);
}
