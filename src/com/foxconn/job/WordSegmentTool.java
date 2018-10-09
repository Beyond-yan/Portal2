package com.foxconn.job;

import java.io.IOException;
import java.io.StringReader;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;


public class WordSegmentTool {
	
	/**
	 * IKAnalyzer中文分词器
	 * @param searchStr
	 * @return 分词后的String
	 * @throws IOException
	 */
	public String segmentWord(String searchStr) throws Exception{
		
		   String segmentWordStr= "";
		   
//			List<Word> words = WordSegmenter.seg(searchStr, SegmentationAlgorithm.BidirectionalMaximumMatching);	
//			for (Word word : words) {
//				segmentWordStr +=word.getText()+" ";
//			}
//			System.out.print(segmentWordStr);
			
		  	StringReader sr=new StringReader(searchStr);  
	        IKSegmenter ik=new IKSegmenter(sr, true);  
	        Lexeme lex=null;  
	        try {
				while((lex=ik.next())!=null){  
					segmentWordStr += lex.getLexemeText()+" ";
				}
				sr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				sr.close();
			}
	        System.out.print(segmentWordStr);  
		return segmentWordStr;
	}

}
