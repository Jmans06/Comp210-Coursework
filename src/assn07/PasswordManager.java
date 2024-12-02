package assn07;

import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password321";
    private Account[] _passwords;
    private int _size;

    public PasswordManager() {
        _passwords = new Account[50];
    }

    // TODO: put
    @Override
    public void put(K key, V value) {
        int positiveHash=Math.abs(key.hashCode());
        int hashNum=positiveHash%_passwords.length;
        if (_passwords[hashNum]!=null && _passwords[hashNum].getWebsite().equals(key)){
            _passwords[hashNum].setPassword(value);
        }
        else if (_passwords[hashNum]!=null){
            Account<K,V> head=_passwords[hashNum];
            while (head.getNext()!=null){
                if (head.getNext().getWebsite().equals(key)){
                    head=head.getNext();
                    head.setPassword(value);
                    _size--;
                    break;
                }
                head=head.getNext();
            }
            head.setNext(new Account<K, V>(key, value));
            _size++;
        }
        else {
            _passwords[hashNum] = new Account<K, V>(key, value);
            _size++;
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int positiveHash=Math.abs(key.hashCode());
        int hashNum=positiveHash%_passwords.length;
        if (_passwords[hashNum]==null){
            return null;
        }
        else if(!(_passwords[hashNum].getWebsite().equals(key))){
            Account<K,V> head=_passwords[hashNum];
            while (head.getNext()!=null){
                if (head.getNext().getWebsite().equals(key)){
                    head=head.getNext();
                    return head.getPassword();
                }
                head=head.getNext();
            }
            return null;
        }
        else{
            Account<K,V> head=_passwords[hashNum];
            return head.getPassword();
        }
    }

    // TODO: size
    @Override
    public int size() {
        return _size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> newSet=new HashSet<>();
        for (int i=0; i<50; i++){
            if (_passwords[i]!=null){
                Account<K, V> head=_passwords[i];
                newSet.add(head.getWebsite());
                while (head.getNext()!=null){
                    head=head.getNext();
                    newSet.add(head.getWebsite());
                }
            }
        }
        return newSet;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int positiveHash=Math.abs(key.hashCode());
        int hashNum=positiveHash%_passwords.length;
        if (_passwords[hashNum]==null){
            return null;
        }
        else if(!(_passwords[hashNum].getWebsite().equals(key))){
            Account<K,V> head=_passwords[hashNum];
            while (head.getNext()!=null){
                if (head.getNext().getWebsite().equals(key)){
                    V password = (V) head.getNext().getPassword();
                    head.setNext(head.getNext().getNext());
                    _size--;
                    return password;
                }
                head=head.getNext();
            }
            return null;
        }
        else{
            Account<K,V> head=_passwords[hashNum];
            V password = head.getPassword();
            _passwords[hashNum]=null;
            _size--;
            return password;
        }
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicates(V value) {
        List<K> newList=new ArrayList<>();
        for (int i=0; i<50; i++){
            if (_passwords[i]!=null){
                Account<K, V> head=_passwords[i];
                if (head.getPassword().equals(value)){
                    newList.add(head.getWebsite());
                }
                while (head.getNext()!=null){
                    head=head.getNext();
                    if (head.getPassword().equals(value)){
                        newList.add(head.getWebsite());
                    }
                }
            }
        }
        return newList;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return enteredPassword.equals(MASTER_PASSWORD);
    }

    @Override
    public String generatesafeRandomPassword(int length) {
        // TODO:
        
        int leftLimit = 48; // hint: numeral '0'=48
        int rightLimit = 122; // hint: letter 'z'=122
        int targetStringLength = length;
        Random random = new Random();

        // TODO: Ensure the minimum length is 4
        if (targetStringLength<4){
            targetStringLength=4;
        }


        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
