/* File: WordSalad.java - April 2018 */
package week09;

/**
 *  Skeleton implementation of the WordSalad class.
 *
 *  @author Michael Albert
 */
public class WordSalad implements Iterable<String> {

    private WordNode first;
    private WordNode last;
     
    public WordSalad() {
        this.first = null;
        this.last = null;
    }

    public WordSalad(java.util.List<String> words) {
        for (String word : words) {
            addLast(word);
        }
    }

    public void add(String word) {
        if (this.first == null) {
            this.first = new WordNode(word, null);
            this.last = this.first;
            return;
        }
        WordNode newFirst = new WordNode(word, this.first);
        this.first = newFirst;
    }

    public void addLast(String word) {
        if (this.first == null) {
            add(word);
            return;
        }
        WordNode newLast = new WordNode(word, null);
        this.last.next = newLast;
        this.last = newLast; 
    }
  
    private class WordNode {
        private String word;
        private WordNode next;
                
        private WordNode(String word, WordNode next) {
            this.word = word;
            this.next = next;
        }
        
    }
  
    public java.util.Iterator<String> iterator() {
        return new java.util.Iterator<String>() {
            private WordNode current = first;
      
            public boolean hasNext() {
                return current != null;
            }
      
            public String next() {
                String result = current.word;
                current = current.next;
                return result;
            }
      
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
                
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        WordNode node = first;
        while (node != null) {
            result.append(node.word);
            result.append(node.next == null ? "" : ", ");
            node = node.next;
        }
        return result.toString() + "]";
    }


    // Method stubs to be completed for the assignment.
    // See the assignment description for specification of their behaviour.

    
    /**
     * Distributes words into k blocks as if dealing cards.
     * @param k the number of blocks.
     * @return WordSalad[] array of split WordSalads.
     */
    public WordSalad[] distribute(int k){
	// Uses distribute with a subset equal to the entire WordSalad.
	return distribute(k, this.first, this.last);
    }
    
    /**
     * Distributes a subset of words into k blocks as if dealing cards.
     * @param k the number of blocks.
     * @param start the first in the subset.
     * @param end the last in the subset.
     * @return WordSalad[] array of split WordSalads.
     */
    private WordSalad[] distribute(int k, WordNode start, WordNode end) {
        int i = 0;
        WordSalad[] hands = new WordSalad[k];
        for (int j=0; j<k; j++){
            hands[j] = new WordSalad();
	}
	WordNode pointer = start;
	String s;
        while (pointer.next != null || pointer != end){
	    s = pointer.word;
	    pointer = pointer.next;
            if (s != null){
                hands[i].addLast(s);
            }
            if(i>=(k-1)){i=0;}else{i++;} // iterate over k
        }
        return hands;
    }

    
    /**
     * Distributes the words into k nearly even length blocks.
     * @param k the number of blocks.
     * @return cellBlock is a WordSalad[] array of split words.
     */
    public WordSalad[] chop(int k) {
	int i = 0;
	WordNode curr = first;
	int count = 0;
        
        // Counts until wordLimit of block has been reached.
	int wordLimitCount = 1;

        //Counts number of words in list
	while(curr!=null){
	    curr=curr.next;
	    i++;
	}

        //The word limit of each WordSalad based off words and blocks.
        int wordLimit = i/k;
        //Finds the remainder to check if extra words need to be added
	int remainder = i%k;

        //Creates new WordSalad of number of blocks.
	WordSalad[] cellBlock = new WordSalad[k];

       
	for(int x = 0; x<k; x++){
	    cellBlock[x] = new WordSalad();
	}

        //Adds words into WordSalad block until block limit has been reached.
        for(String s: this){
	    if(s != null){
		cellBlock[count].addLast(s);
	    }
            
	    if(wordLimitCount<wordLimit+(count<remainder?1:0)){
		wordLimitCount++;
	    }else{
		wordLimitCount = 1;
		count++;
	    }
	}
	return cellBlock;
    }
        
    public WordSalad[] split(int k) {

        
        return null;
    }
        
    public static WordSalad merge(WordSalad[] blocks) {
        return null;
    }
    /**
     * Rejoins a sequence of blocks one after the other.
     * @param blocks the blocks of words that are to be rejoined.
     * @return w the result of rejoining the blocks into one WordSalad.
     */    
    public static WordSalad join(WordSalad[] blocks) {
	WordSalad joined = new WordSalad();
	for(WordSalad block: blocks){
	    for(String s: block){
		joined.addLast(s);
	    }
	}
	return joined;	
    }

    public static WordSalad recombine(WordSalad[] blocks, int k) {
        return null;
    }

}
