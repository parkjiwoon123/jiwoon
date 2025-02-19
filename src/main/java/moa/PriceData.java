package moa;
import java.util.List;

public class PriceData {

    private List<String> dates;  // 가격이 기록된 날짜의 목록. 예: ["2023-10-12", "2023-10-13", ...]
    private List<Double> prices; // 해당 날짜의 가격. 예: [10000.50, 10500.25, ...]

    // 기본 생성자
    public PriceData() {
    }

    // 모든 필드를 파라미터로 받는 생성자
    public PriceData(List<String> dates, List<Double> prices) {
        this.dates = dates;
        this.prices = prices;
    }

    // getters and setters
    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public List<Double> getPrices() {
        return prices;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "PriceData{" +
                "dates=" + dates +
                ", prices=" + prices +
                '}';
    }
}
