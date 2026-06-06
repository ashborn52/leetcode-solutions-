class Solution {
    public String largestNumber(int[] nums) {
        
        List<String> numStrings = new ArrayList<>();
        for (int num : nums) {
            numStrings.add(String.valueOf(num));
        }
      
       
        numStrings.sort((a, b) -> (b + a).compareTo(a + b));
      
      
        if (numStrings.get(0).equals("0")) {
            return "0";
        }
      
      
        return String.join("", numStrings);
    }
}
