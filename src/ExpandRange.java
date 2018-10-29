public class ExpandRange {


    public static boolean isValid(char low,char high){

        if(low >= '0' && low<= '9'){
            if(high >= '0' && high<= '9')
                return true;
        }
        if(low >= 'a' && low <= 'z'){
            if(high >= 'a' && high <= 'z')
                return true;
        }
        if(low >= 'A' && low <= 'Z'){
            if(high >= 'A' && high <= 'Z')
                return true;
        }

        return false;
    }

    public static void expand(String str)
    {
        //Result builder
        StringBuilder result = new StringBuilder();

        //Corner case - count the number of hyphens at the beginning and end of string
        int precount=0;
        int postcount=0;
        int x=0;
        if(str.charAt(0) == '-')
            {
                while(str.charAt(x)=='-')
                    {x++;
                    precount++;}
            }

        x = str.length()-1;
        if(str.charAt(str.length()-1)=='-')
        {
            while(str.charAt(x)=='-')
                {x--;postcount++;}
        }

        //Insert - to result if present in beginning of string
        for(int i=0;i<precount;i++)
            result.append("-");
        //Split the string having - as delimiter
        String p = str;
        String[] arr = p.substring(precount,str.length()-postcount).split("-");


        for (int i = 0; i < arr.length; i++) {
            result.append(arr[i]);
            if (i != arr.length - 1) {
                //rangeLow is the last character of first array element and rangeHigh is the first character of second array
                char rangeLow = arr[i].charAt(arr[i].length()-1);
                char rangeHigh = arr[i + 1].charAt(0);

                //if valid sequence, use the diff between them to insert the values accordingly
                if(isValid(rangeLow,rangeHigh)){
                    int diff = rangeHigh - rangeLow;
                    if(diff > 0) {
                        for (int j = 1; j < Math.abs(diff); j++) {
                            result.append((char) (rangeLow+1));
                            rangeLow+=1;
                        }
                    }
                    else if( diff < 0){
                        for(int j=1 ;j < Math.abs(diff) ; j++){
                            result.append((char) (rangeLow-1));
                            rangeLow-=1;
                        }
                    }
                    else{
                        //diff is 0 i.e 9-9 or a-a
                        result.deleteCharAt(result.length() - 1);
                    }
                }
                else{
                    //invalid sequence, insert a - between them to retain the seq
                    result.append('-');
                }
             }
        }
        //Insert - to result if present at the end of string
        for(int i=0;i<postcount;i++)
            result.append("-");
        System.out.println("Output: " + new String(result));
    }

    // Driver code
    public static void main(String[] args)
    {
        String[] input = {"9-9a-abcH-H","a-fuy0-3za-f","a-f6-3F-9","-ABCD-","-A-D---","0-99-0F-a-","$-a0-9"};

        for(String s : input){
            System.out.println("Input: " + s);
            expand(s);
        }

    }
}
