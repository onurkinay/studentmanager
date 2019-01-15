
fun main() {
    println("Welcome to Student Manager System... Please wait while we're getting the database")

    do{
        try{
        println("--------------------------------------")
        println("Add student -> 1 ")
        println("Delete student -> 2 ")
        println("Change student -> 3 ")
        println("Get student -> 4 ")
        println("--------------------------------------")

        print("Enter menu number: ")
        val menu: Int = readLine().toString().toInt()
        when(menu){
            1 -> {
                println("Enter the student you want to add")
                print("Name:")
                val Name = readLine()

                print("Surname:")
                val Surname = readLine()

                print("Number:")
                val Number = readLine()

                print("Classroom:")
                val Classroom = readLine()

                print("Are you sure of adding the student(yes/no): ")
                val req = readLine()

                if(req == "yes") Menu().AddStudent(Name, Surname, Number, Classroom)
                else "You have just rejected to add student"
            }
            2 -> {
                println("Enter the student's number you want to delete")
                print("Number:")

                val Number = readLine()

                print("Are you sure delete the student(yes/no): ")
                val req = readLine()

                if(req == "yes")Menu().DeleteStudent(Number)
                else "You have just rejected to delete student"

            }
            3 -> {

                println("Enter the student's number you want to change. Remember! You can't change student's number")
                print("Number:")
                val NumberChange = readLine()

                val student: Student = Menu().GetStudent(NumberChange)
                println("If you don't change, you can press Enter")
                print("Name (current: "+student.Name+"):")
                var Name = readLine()

                print("Surname(current: "+student.Surname+"):")
                var Surname = readLine()


                print("Classroom(current: "+student.Class+"):")
                var Classroom = readLine()

                if(Name == "") Name = student.Name
                if(Surname == "") Surname = student.Surname
                if(Classroom == "") Classroom = student.Class

                print("Are you sure change the student(yes/no): ")
                val req = readLine()

                if(req == "yes") Menu().ChangeStudent(Name, Surname, student.Number, Classroom)
                else "You have just rejected to change student"



            }
            4 -> {
                println("Enter the student's number you want to get information")
                print("Number:")
                val NumberChange = readLine()

                val student: Student = Menu().GetStudent(NumberChange)
                println("Name: "+student.Name)
                println("Surname: "+student.Surname)
                println("Number:"+student.Number)
                println("Classroom: "+student.Class)
                readLine()

            } else -> println("Invalid number.")
        }
        }catch (e:Exception){
            println("The program has just detected one or more error!!")
        }
    }while (true)
}
