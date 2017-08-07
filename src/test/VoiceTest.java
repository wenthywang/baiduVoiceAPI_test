/**
 * Copyright(c) Guangzhou JiaxinCloud Science & Technology Ltd. 
 */
package test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.util.FileCopyUtils;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;

/**
 * <pre>
 * 百度语音合成API接口调用。
 * </pre>
 * @author 王文辉  wangwenhui@jiaxincloud.com
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class VoiceTest {
	  //设置APPID/AK/SK
    public static final String APP_ID = "9976314";
    public static final String API_KEY = "S6uEKHN9ATDgZRVGvKo2SHDy";
    public static final String SECRET_KEY = "GPpx9yTTCxUlSh1Go2Es0jSZpRFQU5sX";
    
    public static void main(String[] args) {
    	 // 初始化一个FaceClient
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        
        synthesis(client);
	}
    
    public static void synthesis(AipSpeech client)
    {

        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", "5");
        options.put("pit", "4");
        options.put("per", "4");
        
        String txt="心有多远，就能走多远 ——“泛华金融—俊华公益行”徒步活动";
        txt+="为充分展现活力、专业、亲切的泛华金融员工精神，增强团队凝聚力和战斗力，以更饱满的热情和更强健的体魄投入到工作中，集团于7月21日下午在风景如画的广州市增江画廊举行“心有多远，就能走多远”—2017年“泛华金融-俊华公益行”徒步活动。";
        txt+="近年来，每年一次的徒步活动已成为泛华金融的一种文化，徒步路线或是路程长、或是难度系数高，通过心理与生理的征战，让大家有所收获。此次“泛华金融—俊华公益行”徒步活动，集团总部各职能部门、各事业部以及淘淘金公司全体同事，分为16个小组，共计354人参加这一徒步活动，统一的工装在绿荫上成为了一道亮丽的风景线。"
         +"下午两点，泛华的伙伴们乘着大巴车赶到出发地——初溪休闲公园，气温虽已达33℃，但丝毫挡不住我们如火一般的热情，大家都满怀着信心征战此次徒步行。"
        		+"随着翟总一声令下，由其带领的第一小组率先出发，挥舞着的队旗，剑指20公里。"
         +"我们无惧骄阳，我们心怀梦想。长久以来，我们在喧嚣的楼宇与文牍间行走，很少停下来欣赏沿途的风景。徒步的意义在于一群人携手征战一段旅程，我们发现大自然馈赠了好多让我们流连忘返的地方和值得我们保存的印象！正如此次徒步中，走过一段曲折蜿蜒"
         ;


//        printByteLength(txt, "GBK");   

         String txt2= "的小路，站在转角处，就能看到豁然开朗的增江风景，美不胜收。怪不得伙伴们兴奋地拿起相机在此处跳跃欢呼，记录青春，这不单单是因为此处的美景，更重要的是我们经历一番曲折才得以见得，努力一番远比轻易而来更让人印象深刻。"
        +"短暂的休息是为了能够更好进行下一次征程。像工作一样，充分的工作与充分的休息，劳逸结合，才能够提升工作效率，更好的完成工作。"
        +"转眼里程过半，越到后面，就越来越拼自己的耐力，考验自己的意志力，天将降大任于斯人也,必先苦其心志, 劳其筋骨， 饿其体肤， 空乏其身， 行拂乱其所为， 所以动心忍性， 曾益其所不能。这样的磨练，过程必然是痛苦的，但经历过后，我们会变得更加强大，磨练自己，让自己取得更大的突破，加油，终点就在前方。"
        +"身上的衣衫早已湿透，但越挫越勇的信念，支撑着我们，勇敢的继续走下去。此时，一个关爱的眼神；一句鼓励的话语；一次生命极限的相互寄托，都成为我们心灵中永恒的记忆！"
        +"态度决定一切，33度气温下，20公里的征战，漫长而辛苦，但泛华人一直以坚定的意志力，互助的协作力，果断的行动力，付诸行动。“心有多远，就能走多远”，徒步如同漫步人生，用心、坚持、向前！既然选择出发，那就一路坚持到底。";
//         printByteLength(txt2, "GBK");   
        TtsResponse res = client.synthesis(txt, "zh", 1, options);
        TtsResponse res2 = client.synthesis(txt2, "zh", 1, options);
        System.out.println(res.getErrorCode());
       
        byte[] data = res.getData();
        byte[] data2= res2.getData();
        byte[] dataFinal=byteMerger(data, data2);
        String path="test.mp3";
        File f=new File(path);
        try {
			FileCopyUtils.copy(dataFinal, f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
    
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){  
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];  
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);  
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);  
        return byte_3;  
    }  
    
    public static void printByteLength(String s, String encodingName) {   
        System.out.print("字节数：");   
        try {   
            System.out.print(s.getBytes(encodingName).length);   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
        System.out.println(";编码：" + encodingName);   
    }   
}
