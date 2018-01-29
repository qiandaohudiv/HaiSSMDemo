package cm.cn.mapper;

import cm.cn.po.HlCheckText;
import cm.cn.po.HlCheckTextExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HlCheckTextMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hl_check_text
     *
     * @mbggenerated Thu Sep 21 17:04:30 CST 2017
     */
    int countByExample(HlCheckTextExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hl_check_text
     *
     * @mbggenerated Thu Sep 21 17:04:30 CST 2017
     */
    int deleteByExample(HlCheckTextExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hl_check_text
     *
     * @mbggenerated Thu Sep 21 17:04:30 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hl_check_text
     *
     * @mbggenerated Thu Sep 21 17:04:30 CST 2017
     */
    int insert(HlCheckText record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hl_check_text
     *
     * @mbggenerated Thu Sep 21 17:04:30 CST 2017
     */
    int insertSelective(HlCheckText record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hl_check_text
     *
     * @mbggenerated Thu Sep 21 17:04:30 CST 2017
     */
    List<HlCheckText> selectByExample(HlCheckTextExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hl_check_text
     *
     * @mbggenerated Thu Sep 21 17:04:30 CST 2017
     */
    HlCheckText selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hl_check_text
     *
     * @mbggenerated Thu Sep 21 17:04:30 CST 2017
     */
    int updateByExampleSelective(@Param("record") HlCheckText record, @Param("example") HlCheckTextExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hl_check_text
     *
     * @mbggenerated Thu Sep 21 17:04:30 CST 2017
     */
    int updateByExample(@Param("record") HlCheckText record, @Param("example") HlCheckTextExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hl_check_text
     *
     * @mbggenerated Thu Sep 21 17:04:30 CST 2017
     */
    int updateByPrimaryKeySelective(HlCheckText record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hl_check_text
     *
     * @mbggenerated Thu Sep 21 17:04:30 CST 2017
     */
    int updateByPrimaryKey(HlCheckText record);
}