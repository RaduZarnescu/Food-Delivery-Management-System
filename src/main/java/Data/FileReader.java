package Data;

import Business.BaseProduct;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * Class used to read the data from the given csv file.
 */

public class FileReader {

    public FileReader() {
    }

    public ArrayList<BaseProduct> getProducts() {
        ArrayList<BaseProduct> productsWithDuplicates = new ArrayList<>();
        List<String[]> r = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new java.io.FileReader("products.csv"))) {
            r = reader.readAll();

        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < r.size(); i++) {

            BaseProduct baseProduct = new BaseProduct();
            baseProduct.setTitle(r.get(i)[0]);
            baseProduct.setRating(Double.parseDouble(r.get(i)[1]));
            baseProduct.setCalories(Integer.parseInt(r.get(i)[2]));
            baseProduct.setProtein(Integer.parseInt(r.get(i)[3]));
            baseProduct.setFats(Integer.parseInt(r.get(i)[4]));
            baseProduct.setSodium(Integer.parseInt(r.get(i)[5]));
            baseProduct.setPrice(Integer.parseInt(r.get(i)[6]));
            productsWithDuplicates.add(baseProduct);

        }

        return (ArrayList<BaseProduct>) productsWithDuplicates.stream()
                .filter(distinctByKey(item -> item.getTitle()))
                .collect(Collectors.toList());

    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor){
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
