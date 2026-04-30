package wiseSaying

class App {

    fun run() {
        var lastId = 0
        val wiseSayings = mutableListOf<WiseSaying>()

        println("== 명언 앱 ==")

        while (true) {
            print("명령) ")

            val input = readln()
            val rq = Rq(input)

            when (rq.action) {
                "종료" -> {
                    println("프로그램을 종료합니다.")
                    break
                }

                "등록" -> {
                    print("명언 : ")
                    val content = readln()
                    print("작가 : ")
                    val author = readln()
                    val id = ++lastId

                    WiseSaying(id, content, author)
                        .also { wiseSayings.add(it) }

                    println("${id}번 명언이 등록되었습니다.")
                }

                "목록" -> {
                    println("번호 / 작가 / 명언")
                    println("-".repeat(30))
                    wiseSayings.reversed().forEach {
                        println("${it.id}/ ${it.author} / ${it.content}")
                    }
                }

                "삭제" -> {  //삭제?id=1
                    val id = rq.getParamAsInt("id", 0)

                    if (id == 0) {
                        println("id를 입력해주세요.")
                        continue
                    }

                    val target = wiseSayings.firstOrNull {
                        it.id == id
                    }

                    if (target == null) {
                        println("${id}번 명언은 존재하지 않습니다.")
                        continue
                    }

                    wiseSayings.remove(target)
                    println("${id}번 명언이 삭제되었습니다.")


                    wiseSayings
                        .firstOrNull {
                            it.id == id
                        }
                        ?.let {
                            wiseSayings.remove(it)
                            println("${id}번 명언이 삭제되었습니다.")
                        }
                        ?: println("${id}번 명언은 존재하지 않습니다.")

                }
            }
        }
    }
}