package my.egorrridze.trppconverter;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ValueList {
    List<Value> massValue;
    List<Value> lengthValue;
    List<Value> values;

    ValueList() {
        this.values = new ArrayList<>();

        this.massValue = new ArrayList<>();
        Value kilogram = new Value("кг", 1);
        Value gram = new Value("г", 0.001);
        Value milligram = new Value("мг", 0.000001);
        Value centner = new Value("ц", 100);
        Value tonne = new Value("т", 1000);

        this.massValue.add(kilogram);
        this.massValue.add(gram);
        this.massValue.add(milligram);
        this.massValue.add(centner);
        this.massValue.add(tonne);

        this.values.add(kilogram);
        this.values.add(gram);
        this.values.add(milligram);
        this.values.add(centner);
        this.values.add(tonne);

        this.lengthValue = new ArrayList<>();
        Value meter = new Value("м", 1);
        Value kilometer = new Value("км", 1000);
        Value centimeter = new Value("см", 0.01);
        Value millimeter = new Value("мм", 0.001);

        this.lengthValue.add(meter);
        this.lengthValue.add(kilometer);
        this.lengthValue.add(centimeter);
        this.lengthValue.add(millimeter);
    }

    public List<Value> getMassValue() {
        return massValue;
    }

    public List<Value> getLengthValue() {
        return lengthValue;
    }
}
