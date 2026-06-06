class Solution {
    public String[] findWords(String[] words) {
        
        String keyboardRowMapping = "12210111011122000010020202";
      
       
        List<String> validWords = new ArrayList<>();
      
      
        for (String word : words) {
          
            String lowercaseWord = word.toLowerCase();
          
           
            char targetRow = keyboardRowMapping.charAt(lowercaseWord.charAt(0) - 'a');
          
          
            boolean isValidWord = true;
          
         
            for (char character : lowercaseWord.toCharArray()) {
                if (keyboardRowMapping.charAt(character - 'a') != targetRow) {
                    isValidWord = false;
                    break;
                }
            }
          
         
            if (isValidWord) {
                validWords.add(word);
            }
        }
      
     
        return validWords.toArray(new String[0]);
    }
}