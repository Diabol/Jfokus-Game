
@Grab(group = "com.xlson.groovycsv", module="groovycsv", version="0.2")
import com.xlson.groovycsv.CsvParser

def file = new File(args[0])

def data = new CsvParser().parse(file.text)

//Question,Correct Answer,False Answer 1,False Answer 2,False Answer 3
number = 1;

def random = new Random()

class Answer {
    String answer
    boolean correct
}

for (line in data) {
    def question = line.Question.trim()
    def correct = line.'Correct Answer'.trim()
    def false1 = line.'False Answer 1'.trim()
    def false2 = line.'False Answer 2'.trim()
    def false3 = line.'False Answer 3'.trim()

    def list = new ArrayList<Answer>()
    list.add(new Answer(answer: correct, correct: true))
    list.add(new Answer(answer: false1, correct: false))
    list.add(new Answer(answer: false2, correct: false))
    list.add(new Answer(answer: false3, correct: false))

    println("Question($number):")
    println("  text: \"$question\"")
    println("  answers: [" + number + "A, "+ number + "B, " + number + "C, " + number + "D]")

    def answer = list.remove(random.nextInt(4))
    println("  ")
    println("Answer("+ number +"A):")
    println("  text: \"$answer.answer\"")
    println("  correct: $answer.correct")
    println("  question: $number")

    answer = list.remove(random.nextInt(3))
    println("  ")
    println("Answer("+ number +"B):")
    println("  text: \"$answer.answer\"")
    println("  correct: $answer.correct")
    println("  question: $number")

    answer = list.remove(random.nextInt(2))
    println("  ")
    println("Answer("+ number +"C):")
    println("  text: \"$answer.answer\"")
    println("  correct: $answer.correct")
    println("  question: $number")

    answer = list.remove(random.nextInt(1))
    println("  ")
    println("Answer("+ number +"D):")
    println("  text: \"$answer.answer\"")
    println("  correct: $answer.correct")
    println("  question: $number")

    println("")
    number++


}