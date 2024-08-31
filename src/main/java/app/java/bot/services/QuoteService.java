package app.java.bot.services;

import app.java.bot.models.Quote;
import app.java.bot.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class QuoteService {
    @Autowired
    BashParser parser;
    @Autowired
    QuoteRepository repository;

    // Code for receiving records. For first run only
//    public List<Quote> getIndex() {
//        List<Quote> ret = new ArrayList<>();
//
//        try {
//            Map<Integer, String> map = parser.getIndex();
//
//            for (var entry : map.entrySet()) {
//                var rawQuote = new Quote();
//                rawQuote.setQuoteId(entry.getKey());
//                rawQuote.setText(entry.getValue());
//                var existed = repository.findByQuoteIdEquals(rawQuote.getQuoteId());
//
//                if (existed.isEmpty()) {
//                    ret.add(repository.save(rawQuote));
//                } else {
//                    ret.add(existed.get());
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return ret;
//    }

    public List<Quote> getPage(int page) {
        List<Quote> ret = new ArrayList<>();
        Map<Integer, String> map = parser.getPage(page);

        for (var entry : map.entrySet()) {
            var rawQuote = new Quote();
            rawQuote.setQuoteId(entry.getKey());
            rawQuote.setText(entry.getValue());
            var existed = repository.findByQuoteIdEquals(rawQuote.getQuoteId());

            if (existed.isEmpty()) {
                ret.add(repository.save(rawQuote));
            } else {
                ret.add(existed.get());
            }
        }

        return ret;
    }

    public Quote getById(int id) {
        var existingQuote = repository.findByQuoteIdEquals(id);

        if (existingQuote.isPresent()) {
            return existingQuote.get();
        }

        var quoteEntry = parser.getById(id);
        if (quoteEntry == null) return null;
        var newQuote = new Quote();
        newQuote.setQuoteId(quoteEntry.getKey());
        newQuote.setText(quoteEntry.getValue());
        return repository.save(newQuote);

    }

    public Quote getRandom() {
        var quoteEntry = parser.getRandom();
        if (quoteEntry == null) return null;

        var existingQuote = repository.findByQuoteIdEquals(quoteEntry.getKey());

        if (existingQuote.isPresent()) {
            return existingQuote.get();
        }

        var newQuote = new Quote();
        newQuote.setQuoteId(quoteEntry.getKey());
        newQuote.setText(quoteEntry.getValue());
        return repository.save(newQuote);
    }
}