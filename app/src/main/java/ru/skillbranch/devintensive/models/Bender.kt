package ru.skillbranch.devintensive.models

class Bender (var  status : Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion():String = when (question) {
        Question.NAME-> Question.NAME.question
        Question.PROFESSION-> Question.PROFESSION.question
        Question.MATERIAL-> Question.MATERIAL.question
        Question.BDAY-> Question.BDAY.question
        Question.SERIAL-> Question.SERIAL.question
        Question.IDLE-> Question.IDLE.question
    }

    fun listenAnswer(answer:String) : Pair<String,Triple<Int,Int,Int>> {
        return if(question == Question.IDLE || question.answer.contains(answer)) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            val addEnd = if(status == Status.CRITICAL) { question = Question.NAME; ". Давай все по новой" } else ""
            status = status.nextStratus()
            "${"Это неправильный ответ"}$addEnd\n${question.question}" to status.color
        }
    }
    enum class  Status(val color : Triple<Int,Int,Int>) {
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0)) ;

        fun nextStratus(): Status {
            return if(this.ordinal< values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values().first()
            }
        }
    }
    enum class Question(val question: String,val answer:List<String>) {
        NAME("Как меня зовут?", listOf("Бендер", "bender"))  {
            override fun nextQuestion(): Question = PROFESSION
            override fun validation(): String =
                "Имя должно начинаться с заглавной буквы"

        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL
            override fun validation(): String =
                "Профессия должна начинаться со строчной буквы"
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
            override fun validation(): String =
                "Материал не должен содержать цифр"
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
            override fun validation(): String =
                "Год моего рождения должен содержать только цифры"
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
            override fun validation(): String =
                "Серийный номер содержит только цифры, и их 7"
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
            override fun validation(): String =
                ""
        };

        abstract fun nextQuestion() : Question
        abstract fun validation() : String
    }
}