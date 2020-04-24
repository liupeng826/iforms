DELIMITER $$
drop procedure if exists `SP_QueryAnswerData`;
CREATE PROCEDURE `SP_QueryAnswerData`(IN formid varchar(100))
    READS SQL DATA
BEGIN

DECLARE cnt INT DEFAULT 0;

drop table if exists `tmp_answer`;
create table tmp_answer (SELECT T1.`formTitle`, T1.`answer_id`,T1.`title`, ifnull(`T1`.`description`,T1.new_answer_value) as answer from (
SELECT `form`.`title` as formTitle,
	   `question`.`title`,
       `answer`.`answer_id`,
       `answer`.`super_question_id`,
       `question`.`question_type_id`,
       `answer`.`super_option_id`,
(CASE question.question_type_id 
	WHEN 1 THEN answer.answer_option_id
	WHEN 2 THEN answer.answer_option_id
	WHEN 3 THEN answer.answer_description
	WHEN 4 THEN answer.answer_value
	WHEN 5 THEN answer.answer_description
	WHEN 6 THEN answer.answer_value
	ELSE ''
END) as new_answer_value,
`question_option`.`description`
FROM answer
left join form on `form`.`super_form_id` = `answer`.`super_form_id` and `form`.`language` = 'en-us'
left join question on `question`.`id` = `answer`.`question_id`
and `answer`.`super_question_id` = `question`.`super_question_id`
 and `question`.`language` = 'en-us' 
left join question_option on `answer`.`super_option_id` = `question_option`.`super_option_id` and `question_option`.`language` = 'en-us'
where `answer`.`super_form_id` = formid
) T1);

SELECT count(1) into cnt from tmp_answer;
IF cnt is not null and cnt > 0 THEN
	SET @sql = NULL;
	SELECT
	  GROUP_CONCAT(DISTINCT
		CONCAT('MAX(IF(c.title = ''',c.title,''', c.answer, null)) AS ''',c.title, '''')
	  ) INTO @sql
	FROM tmp_answer c;

	SET @sql = CONCAT('Select c.`formTitle`, c.`answer_id`, ', @sql, 
							' From tmp_answer c
							Group by c.`answer_id`');
	PREPARE stmt FROM @sql;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
END IF;

END$$
DELIMITER ;