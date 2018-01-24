public class Trie<T> {
    TrieNode children[];

    public Trie() {
        children = new TrieNode[26];
    }

    public void insert(String word, T data) {
        word = word.toLowerCase();
        TrieNode ch[] = children;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';

            TrieNode node;
            if (ch[index] != null){
                node = ch[index];
            } else {
                node = new TrieNode();
                ch[index] = node;
            }

            ch = node.children;
            if (i == word.length() - 1){
                node.setEnd(true);
                node.setData(data);
            }
        }
    }

    public T get(String word) {
        word = word.toLowerCase();
        int index = -1;
        TrieNode ch[] = children;
        TrieNode node = null;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            index = c - 'a';

            if (ch[index] != null){
                node = ch[index];
            } else {
                node = new TrieNode();
                ch[index] = node;
            }

            ch = node.children;

            if (i == word.length() - 1){
                node.setEnd(true);
            }

        }
        if (index == -1)
            return null;

        if (node == null)
            return null;

        return (T) node.getData();
    }


}

class TrieNode<T> {
    private T data;
    char c;//TODO
    TrieNode children[];
    private boolean isEnd;

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TrieNode(T data, boolean isEnd) {
        this.data = data;
        this.isEnd = isEnd;
        children = new TrieNode[26];
    }

    public TrieNode() {
        this(null, false);
    }

}