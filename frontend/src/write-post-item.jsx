import { useState } from "react";

function WritePostItem() {
  const [post, setPost] = useState({
    title: "",
    content: "",
    nickname: localStorage.getItem("MY_NICKNAME"),
  });

  async function updatePost() {
    const UPLOAD_POST_URL = `https://a15c8e94-d677-49fb-910f-e2d7bafbd878.mock.pstmn.io/api/post`;
    const body = JSON.stringify(post);
    const response = await fetch(UPLOAD_POST_URL, {
      method: "POST",
      body: body,
    });
    const data = await response.json();
    setPost(data);
    localStorage.setItem("MY_NICKNAME", post.nickname);
    window.alert(`게시물을 등록했습니다.`);
  }

  return (
    <div style={{ width: "100%", height: "100%" }}>
      <p style={{ border: "1px solid black" }}>
        titile:{" "}
        <input
          value={post?.title}
          onChange={(e) =>
            setPost((prev) => {
              return { ...prev, title: e.target.value };
            })
          }
        />
      </p>
      <p style={{ border: "1px solid black" }}>
        nickname:{" "}
        <input
          value={post?.nickname}
          onChange={(e) =>
            setPost((prev) => {
              return { ...prev, nickname: e.target.value };
            })
          }
        />
      </p>
      <p style={{ border: "1px solid black" }}>
        content:{" "}
        <input
          value={post?.content}
          onChange={(e) =>
            setPost((prev) => {
              return { ...prev, content: e.target.value };
            })
          }
        />
      </p>
      <div
        style={{
          display: "flex",
          gap: "10px",
          alignItems: "center",
          border: "1px solid grey",
        }}
      >
        <input
          type="checkbox"
          id="priority"
          name="priority"
          checked={post?.isChecked}
          onChange={() => {
            setPost((prev) => {
              return { ...prev, isChecked: !prev.isChecked };
            });
          }}
        />
        <label for="priority">중요 게시물 여부</label>
      </div>
      <button style={{ height: "30px" }} onClick={() => updatePost()}>
        확인
      </button>
    </div>
  );
}

export default WritePostItem;
