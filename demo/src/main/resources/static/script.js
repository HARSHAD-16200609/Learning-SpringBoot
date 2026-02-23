const text = document.querySelector(".input")
const post = document.querySelector(".post")
const title = document.querySelector(".title")
const author = document.querySelector(".authName")

post.addEventListener("click", async () => {

  try {
    const blog = text.value;
    const tit = title.value;
    const auth = author.value;
    await axios.post("http://localhost:8080/api/message", {
      content: blog,
      user: auth,
      title: tit
    }, {
      headers: { "Content-Type": "application/json" }
    });
    console.log("sucess");
  }
  catch (e) {
    console.log("erorr : " + e)
  }
})


