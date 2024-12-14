export default interface CommentListItem {
    commentNumber: number;
    nickname: string;
    profileImage: string | null;
    writeDatetime: string;
    content: string;
    parentCommentNumber: number | null;
    replies?: CommentListItem[];  // 대댓글 목록
}