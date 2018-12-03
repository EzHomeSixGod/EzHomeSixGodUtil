package com.ezhomesixgod.prize;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;


/**
 * @author renyang
 * @description
 * @createTime 2018-11-27 10:25
 */
public class PrizeUtil {

    private static final Logger log =LoggerFactory.getLogger(PrizeUtil.class);


    /**
     * 一夜暴富产生头奖
     * @param gameYybfs 一夜暴富用户参与记录
     * @param tbGameYybf 一夜暴富配置信息
     * @return
     */
    public static String createReward(List<TbUserGameYybfVo> gameYybfs, TbGameYybf tbGameYybf){
        List<RewardVo> result = new ArrayList<>();
        log.info("----------------游戏金币:"+tbGameYybf.getPoint().doubleValue());
        BigDecimal reward = tbGameYybf.getPoint().multiply(new BigDecimal(0.8));
        log.info("----------------奖池 ："+reward.doubleValue()+"金币");
        BigDecimal devide = new BigDecimal(8);
        BigDecimal oneDevide = new BigDecimal(5);
        BigDecimal twoDevide = new BigDecimal(2);
        BigDecimal threeDevide = new BigDecimal(1);

        int oneReward = reward.divide(devide).multiply(oneDevide).intValue();
        double twoReward = reward.divide(devide).multiply(twoDevide).intValue();
        double threeReward = reward.divide(devide).multiply(threeDevide).intValue();
        log.info("一等奖金币:{};二等奖金币:{};三等奖金币:{}",oneReward,twoReward,threeReward);

        BigDecimal[] bigDecimals = new BigDecimal[]{new BigDecimal(oneReward),new BigDecimal(twoReward),new BigDecimal(threeReward)};
        List<Integer> users =new ArrayList<>();
        for(int i=0;i<3;i++){
            int index = drawGift(gameYybfs);
            Integer userId = gameYybfs.get(index).getUserId();
            if(users.contains(userId) ){
                i--;
            }else{
                users.add(userId);
                RewardVo vo =new RewardVo();
                vo.setName(gameYybfs.get(index).getName());
                vo.setHead(gameYybfs.get(index).getHead());
                vo.setId(userId);
                vo.setPoint(bigDecimals[i]);
                result.add(vo);
            }
        }
        return new Gson().toJson(result);
    }


    public static int drawGift(List<TbUserGameYybfVo> giftList){

        if(null != giftList && giftList.size()>0){
            List<Double> orgProbList = new ArrayList<Double>(giftList.size());
            for(TbUserGameYybf gift:giftList){
                //按顺序将概率添加到集合中
                orgProbList.add(gift.getPoint().doubleValue());
            }
            return draw(orgProbList);
        }
        return -1;
    }

    public static int draw(List<Double> giftProbList){

        List<Double> sortRateList = new ArrayList<Double>();
        // 计算概率总和
        Double sumRate = 0D;
        for(Double prob : giftProbList){
            sumRate += prob;
        }

        if(sumRate != 0){
            double rate = 0D;   //概率所占比例
            for(Double prob : giftProbList){
                rate += prob;
                // 构建一个比例区段组成的集合(避免概率和不为1)
                sortRateList.add(rate / sumRate);
            }

            // 随机生成一个随机数，并排序
            double random = Math.random();
            sortRateList.add(random);
            Collections.sort(sortRateList);

            // 返回该随机数在比例集合中的索引
            return sortRateList.indexOf(random);
        }
        return -1;
    }
}
