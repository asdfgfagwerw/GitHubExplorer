package aj.demo.githubexplorer;

public class Parser {

    public static String parseSubsequentPageUrl(String subsequentPageUrl) {
        if(subsequentPageUrl == null || subsequentPageUrl.isEmpty()) {
            return null;
        }
        String result = subsequentPageUrl.substring(1, subsequentPageUrl.indexOf('>'));
        return result;
    }

    public static String parseIssuesUrl(String malformedUrl) {
        if(malformedUrl == null || malformedUrl.isEmpty()) {
            return null;
        }
        String result = malformedUrl.substring(0, malformedUrl.indexOf('{'));
        return result;
    }

    public static String extractDate(String badDateFormat) {
        if(badDateFormat == null || badDateFormat.isEmpty()) {
            return null;
        }
        String wellFormedDate = badDateFormat.replace('T', ' ');
        wellFormedDate = wellFormedDate.replace("Z", "");
        return wellFormedDate;
    }

    private Parser() {
        /**
         * private constructor to disable instantiation of this class
         */
    }
}
