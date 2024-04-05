package jmsa;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import objects.Trade;

public class JSONTradesMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ObjectMapper objectMapper = new ObjectMapper();
		
//		String jsonString = "[{\"cusip\":\"null\",\"secSID\":1,\"maturityDate\":\"2031-03-05\"},"
//					 	    +"{\"cusip\":\"null\",\"secSID\":2,\"maturityDate\":\"2027-03-05\"},"
//					 	    +"{\"cusip\":\"null\",\"secSID\":6,\"maturityDate\":\"2019-12-06\"},"
//					 	    +"{\"cusip\":\"null\",\"secSID\":7,\"maturityDate\":\"2022-03-04\"}]";
		
		File jsonFile = new File("src/main/resources/SampleTrades.json");
		
		// For Date conversion
        LocalDate localDate;
		
		try {
			
			List<Trade> trades = Arrays.asList(objectMapper.readValue(jsonFile, Trade[].class));
			
			System.out.println("Original Trades List:");
            for (Trade trade : trades) {
            	
            	// Convert Date to LocalDate
            	localDate = trade.getMaturityDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            	
                System.out.println("cusip: " + trade.getCusip() 
                		+ ", secSID: " + trade.getSecSID() 
                		+ ", maturityDate: " + localDate
                		);
            }
			
//			 ( <= '2023-01-01' & >= '2020-12-31' ) | ( <= '2028-01-01' & >= '2025-12-31' )"
            
            // Dates for Filter
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			String startDateString1 = "2020-12-31";
			String endDateString1 = "2023-01-01";
			Date startDate1 = format.parse(startDateString1);
			Date endDate1 = format.parse(endDateString1);
			
			String startDateString2 = "2025-12-31";
			String endDateString2 = "2028-01-01";
			Date startDate2 = format.parse(startDateString2);
			Date endDate2 = format.parse(endDateString2);
			
			// Filter List
			List<Trade> filteredTradesList = trades.stream()
												.filter(trade -> (trade.getMaturityDate().compareTo(startDate1) >= 0 && trade.getMaturityDate().compareTo(endDate1) <= 0)
																	||
																 (trade.getMaturityDate().compareTo(startDate2) >= 0 && trade.getMaturityDate().compareTo(endDate2) <= 0)
												).collect(Collectors.toList());
			
			System.out.println("Filtered Trades List:");
            for (Trade trade : filteredTradesList) {
            	
                System.out.println("cusip: " + trade.getCusip() 
        					  + ", secSID: " + trade.getSecSID() 
        				+ ", maturityDate: " + trade.getMaturityDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                		);

            }
            
            // Order List
            Collections.sort(filteredTradesList, Comparator.comparing(Trade::getMaturityDate));
            
			System.out.println("Ordered Trades List:");
            for (Trade trade : filteredTradesList) {
            	
                System.out.println("cusip: " + trade.getCusip() 
        					  + ", secSID: " + trade.getSecSID() 
        				+ ", maturityDate: " + trade.getMaturityDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                		);

            }

			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
