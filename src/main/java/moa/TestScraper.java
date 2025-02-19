package moa;

public class TestScraper {
    public static void main(String[] args) {
        int siteIdForCoupang = 1;
        int siteIdForGMarket = 2;
        int siteIdForThirdSite = 3; // 3번 사이트에 대한 ID 추가

        String coupangUrl = "https://www.coupang.com/vp/products/132992369?itemId=739923995&vendorItemId=4868491712&sourceType=cmgoms&omsPageId=100196&omsPageUrl=100196&isAddedCart=";
        String gmarketUrl = "http://item.gmarket.co.kr/Item?goodscode=2523273487	";
        String thirdSiteUrl = "https://www.11st.co.kr/products/2925445226?trTypeCd=03&trCtgrNo=2138100";  // 3번 사이트의 URL을 여기에 입력하세요

        gogoVO coupangResult = gogo.scrapeData(siteIdForCoupang, coupangUrl);
        gogoVO gmarketResult = gogo.scrapeData(siteIdForGMarket, gmarketUrl);
        gogoVO thirdSiteResult = gogo.scrapeData(siteIdForThirdSite, thirdSiteUrl); // 3번 사이트 결과 스크래핑

        System.out.println("쿠팡 결과:");
        System.out.println("이름: " + coupangResult.getName());
        System.out.println("가격: " + coupangResult.getPrice());
        System.out.println();

        System.out.println("G마켓 결과:");
        System.out.println("이름: " + gmarketResult.getName());
        System.out.println("가격: " + gmarketResult.getPrice());
        System.out.println();

        System.out.println("3번 사이트 결과:"); // 3번 사이트의 결과 출력
        System.out.println("이름: " + thirdSiteResult.getName());
        System.out.println("가격: " + thirdSiteResult.getPrice());
    }
}
