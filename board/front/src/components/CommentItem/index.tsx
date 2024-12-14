import React, { useState } from 'react';
import './style.css';
import { CommentListItem } from 'types';
import DefaultProfileImage from 'assets/default-profile-image.png';
import dayjs from 'dayjs';
import utc from 'dayjs/plugin/utc';
import timezone from 'dayjs/plugin/timezone';

// dayjs 플러그인 설정
dayjs.extend(utc);
dayjs.extend(timezone);
dayjs.tz.setDefault('Asia/Seoul');

//          interface: 댓글 리스트 아이템 컴포넌트 Props         //
interface Props {
  commentItem: CommentListItem;
  onReplySubmit: (content: string, parentCommentNumber: number) => void;
  depth?: number;  // 댓글 깊이 (대댓글 여부 확인)
}

//          component: 댓글 리스트 아이템 컴포넌트          //
export default function CommentItem({ commentItem, onReplySubmit, depth = 0 }: Props) {

  //          state: Properties          //
  const { commentNumber, content, profileImage, writeDatetime, nickname, replies } = commentItem;
  const [showReplyInput, setShowReplyInput] = useState<boolean>(false);
  const [replyContent, setReplyContent] = useState<string>('');
  const [showReplies, setShowReplies] = useState<boolean>(false);

  //          event handler: 대댓글 버튼 클릭 이벤트 처리          //
  const handleReplyClick = () => {
    setShowReplyInput(!showReplyInput);
  };

  //          event handler: 대댓글 내용 변경 이벤트 처리          //
  const handleReplyContentChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    setReplyContent(event.target.value);
  };

  //          event handler: 대댓글 제출 이벤트 처리          //
  const handleSubmitReply = () => {
    if (!replyContent.trim()) return;
    onReplySubmit(replyContent, commentNumber);
    setReplyContent('');
    setShowReplyInput(false);
  };

  //          function: 작성일 경과시간 함수          //
  const getElapsedTime = () => {
    const now = dayjs().add(9, 'hour');
    const writeTime = dayjs(writeDatetime);

    const gap = now.diff(writeTime, 's');
    if (gap < 60) return `${gap}초 전`;
    if (gap < 3600) return `${Math.floor(gap/60)}분 전`;
    if (gap < 86400) return `${Math.floor(gap/3600)}시간 전`;
    return `${Math.floor(gap/86400)}일 전`;
  };

  //          render: 댓글 리스트 아이템 컴포넌트 렌더링          //
  return (
    <div className={`comment-list-item-box ${depth > 0 ? 'reply-item' : ''}`}>
      <div className='comment-list-item-top'>
        <div className='comment-list-item-profile-box'>
          <div 
            className='comment-list-item-profile-image' 
            style={{ backgroundImage: `url(${profileImage || DefaultProfileImage})` }}
          />
        </div>
        <div className='comment-list-item-nickname'>{nickname}</div>
        <div className='comment-list-item-time'>{getElapsedTime()}</div>
      </div>
      <div className='comment-list-item-main'>
        <div className='comment-list-item-content'>{content}</div>
        {depth === 0 && (
          <div className='comment-actions'>
            <div className='comment-list-item-reply-button' onClick={handleReplyClick}>
              답글 달기
            </div>
            {replies && replies.length > 0 && (
              <div 
                className='comment-list-item-toggle-replies' 
                onClick={() => setShowReplies(!showReplies)}
              >
                {showReplies ? '답글 숨기기' : `답글 ${replies.length}개 보기`}
              </div>
            )}
          </div>
        )}
        {showReplyInput && (
          <div className='reply-input-container'>
            <textarea
              className='reply-input'
              value={replyContent}
              onChange={handleReplyContentChange}
              placeholder='답글을 입력하세요...'
            />
            <div className='reply-button-group'>
              <button 
                className={`reply-submit-button ${replyContent.trim() ? 'active' : ''}`}
                onClick={handleSubmitReply}
              >
                답글달기
              </button>
            </div>
          </div>
        )}
      </div>
      {replies && replies.length > 0 && showReplies && (
        <div className='replies-container'>
          {replies.map((reply: CommentListItem) => (
            <CommentItem 
              key={reply.commentNumber}
              commentItem={reply}
              onReplySubmit={onReplySubmit}
              depth={depth + 1}
            />
          ))}
        </div>
      )}
    </div>
  )
}
