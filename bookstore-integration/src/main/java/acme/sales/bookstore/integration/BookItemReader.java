package acme.sales.bookstore.integration;

import acme.sales.bookstore.domain.entities.Book;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vmuravlev
 */
public class BookItemReader extends FlatFileItemReader<Book> {

    public BookItemReader() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(";");
        tokenizer.setNames(new String[]{"author", "title", "genre", "price"});

        BeanWrapperFieldSetMapper<Book> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Book.class);

        Map<Class, PropertyEditor> customEditors = new HashMap<>();
        customEditors.put(Double.class, new CSVDoublePropertyEditor());
        fieldSetMapper.setCustomEditors(customEditors);

        DefaultLineMapper<Book> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        setLineMapper(lineMapper);
    }

    private static class CSVDoublePropertyEditor extends PropertyEditorSupport {
        private Double dblValue;
        private DecimalFormat csvFormat;

        public CSVDoublePropertyEditor() {
            csvFormat = new DecimalFormat();
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dfs.setDecimalSeparator(',');
            csvFormat.setDecimalFormatSymbols(dfs);
        }

        @Override
        public Object getValue() {
            return dblValue;
        }

        @Override
        public void setValue(Object value) {
            if (value != null && value instanceof String) {
                setFromString((String) value);
            } else {
                super.setValue(value);
            }
        }

        private void setFromString(String strValue) {
            try {
                this.dblValue = csvFormat.parse(strValue).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
                this.dblValue = Double.NaN;
            }
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setFromString(text);
        }
    }
}