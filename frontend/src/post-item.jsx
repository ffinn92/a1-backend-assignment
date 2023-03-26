import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";

function PostItem() {
  const { postId } = useParams();
  const [post, setPost] = useState();

  async function requestPost(postId) {
//     const GET_POST_URL = `https://a15c8e94-d677-49fb-910f-e2d7bafbd878.mock.pstmn.io/api/post/${postId}`;
    const GET_POST_URL = `http://localhost:8080/api/posts/${postId}`;
    const response = await fetch(GET_POST_URL, {
      method: "GET",
    });
    const data = await response.json();
    setPost(data);
  }

  async function deletePost() {
    console.log(post);
//     const GET_DELETE_URL = `https://a15c8e94-d677-49fb-910f-e2d7bafbd878.mock.pstmn.io/api/post/{postId}`;
    const GET_DELETE_URL = `http://localhost:8080/api/posts`;
    const body = JSON.stringify({id:postId});
    await fetch(GET_DELETE_URL, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json"
      },
      body: body,
    });
    window.alert(`게시물을 삭제했습니다. 뒤로가기를 눌러주세요.`);
  }

  useEffect(() => {
    requestPost(postId);
  }, []);

  return (
    <div style={{ width: "100%", height: "100%" }}>
      <p style={{ border: "1px solid black" }}>titile: {post?.title}</p>
      <p style={{ border: "1px solid black" }}>nickname: {post?.nickname}</p>
      <p style={{ border: "1px solid black" }}>content: {post?.content}</p>
      <div
        style={{ display: "flex", justifyContent: "space-between", gap: "4px" }}
      >
        <Link to={`/edit/${postId}`}>
          <button style={{ height: "30px" }}>수정</button>
        </Link>
        <button style={{ height: "30px" }} onClick={() => deletePost(postId)}>
          삭제
        </button>
      </div>
    </div>
  );
}

export default PostItem;
