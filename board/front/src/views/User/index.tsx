import React, { ChangeEvent, useEffect, useRef, useState } from 'react';
import './style.css';
import { useNavigate, useParams } from 'react-router-dom';
import { userBoardListMock, userMock } from 'mocks';
import { useUserStore } from 'stores';
import { usePagination } from 'hooks';
import { BoardListItem } from 'types';
import BoardItem from 'components/BoardItem';
import Pagination from 'components/Pagination';
import { AUTH_PATH, BOARD_WRITE_PATH, MAIN_PATH, USER_PATH } from 'constant';
import { fileUploadRequest, getSignInUserRequest, getUserBoardListRequest, getUserRequest, patchNicknameRequest, patchProfileImageRequest } from 'apis';
import { GetSignInUserResponseDto, GetUserResponseDto } from 'apis/dto/response/user';
import ResponseDto from 'apis/dto/response';
import { GetUserBoardListResponseDto } from 'apis/dto/response/board';
import { useCookies } from 'react-cookie';
import { PatchBoardRequestDto } from 'apis/dto/request/board';
import { PatchNicknameRequestDto, PatchProfileImageRequestDto } from 'apis/dto/request/user';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import type { Value } from 'react-calendar/dist/cjs/shared/types';

//          component: 유저 페이지          //
export default function User() {

  //          state: 조회하는 유저 이메일 path variable 상태           //
  const { searchEmail } = useParams();
  //          state: 로그인 유저 정보 상태           //
  const { user, setUser } = useUserStore();
  //          state: 본인 여부 상태           //
  const [isMyPage, setMyPage] = useState<boolean>(false);

  //          state: 캘린더 관련 상태 추가           //
  const [selectedDate, setSelectedDate] = useState<Date | null>(null);
  const [showDiaryModal, setShowDiaryModal] = useState<boolean>(false);
  const [diaryContent, setDiaryContent] = useState<string>('');
  const [diaryEntries, setDiaryEntries] = useState<{[key: string]: string}>({});

  //          function: 네비게이트 함수          //
  const navigator = useNavigate();

  // 캘린더 핸들러들 추가
  const handleDateClick = (value: Value, event: React.MouseEvent<HTMLButtonElement>) => {
    if (!value || value instanceof Array) return;
    setSelectedDate(value);
    setShowDiaryModal(true);
    const dateStr = value.toISOString().split('T')[0];
    setDiaryContent(diaryEntries[dateStr] || '');
  };

  const handleCloseModal = () => {
    setShowDiaryModal(false);
    document.body.classList.remove('modal-open');
  };

  const tileContent = ({ date }: { date: Date }) => {
    const dateStr = date.toISOString().split('T')[0];
    return diaryEntries[dateStr] ? <div className="diary-icon">💪</div> : null;
  };

  const handleSaveDiary = () => {
    if (!selectedDate) return;
    const dateStr = selectedDate.toISOString().split('T')[0];
    setDiaryEntries(prev => ({
      ...prev,
      [dateStr]: diaryContent
    }));
    setShowDiaryModal(false);
    document.body.classList.remove('modal-open');
  };

  //          component: 유저 정보 컴포넌트          //
  const UserInfo = () => {

    //          state: 프로필 이미지 input ref 상태           //
    const fileInputRef = useRef<HTMLInputElement | null>(null);
    //          state: cookie 상태           //
    const [cookies, setCookie] = useCookies();
    //          state: 이메일 상태           //
    const [email, setEmail] = useState<string>('');
    //          state: 프로필 이미지 상태           //
    const [profileImage, setProfileImage] = useState<string | null>('');
    //          state: 기존 닉네임 상태           //
    const [existingNickname, setExistingNickname] = useState<string>('');
    //          state: 닉네임 상태           //
    const [nickname, setNickname] = useState<string>('');
    //          state: 닉네임 변경 상태           //
    const [showChangeNickname, setShowChangeNickname] = useState<boolean>(false);

    //          function: get user response 처리 함수          //
    const getUserResponse = (responseBody: GetUserResponseDto | ResponseDto) => {
      const { code } = responseBody;
      if (code === 'NU') alert('존재하지 않는 유저입니다.');
      if (code === 'DBE') alert('데이터베이스 오류입니다.');
      if (code !== 'SU') {
        navigator(MAIN_PATH);
        return;
      }

      const { email, nickname, profileImage } = responseBody as GetUserResponseDto;
      setEmail(email);
      setNickname(nickname);
      setExistingNickname(nickname);
      setProfileImage(profileImage);
    };
    //          function: patch nickname response 처리 함수          //
    const patchNicknameResponse = (code: string) => {
      if (code === 'AF' || code === 'NU') {
        alert('로그인이 필요합니다.');
        navigator(AUTH_PATH);
        return;
      }
      if (code === 'VF') alert('빈 값일 수 없습니다.');
      if (code === 'DN') alert('중복되는 닉네임입니다.');
      if (code === 'DBE') alert('데이터베이스 오류입니다.');
      if (code !== 'SU') {
        setNickname(existingNickname);
        return;
      }

      if (!searchEmail) return;
      getUserRequest(searchEmail).then(getUserResponse);

      const accessToken = cookies.accessToken;
      if (!accessToken) return;
      getSignInUserRequest(accessToken).then(getSignInUserResponse);

      setShowChangeNickname(false);
    };
    //          function: file upload response 처리 함수          //
    const fileUploadResponse = (url: string | null) => {
      const accessToken = cookies.accessToken;
      if (!accessToken) return;

      const requestBody: PatchProfileImageRequestDto = {
        profileImage: url
      };
      patchProfileImageRequest(requestBody, accessToken).then(patchProfileImageResponse);
    };
    //          function: patch profile image response 처리 함수          //
    const patchProfileImageResponse = (code: string) => {
      if (code === 'NU' || code === 'AF') {
        navigator(AUTH_PATH);
        return;
      }
      if (code === 'DBE') alert('데이터베이스 오류입니다.');
      if (code !== 'SU') return;

      if (!searchEmail) return;
      getUserRequest(searchEmail).then(getUserResponse);

      const accessToken = cookies.accessToken;
      if (!accessToken) return;
      getSignInUserRequest(accessToken).then(getSignInUserResponse);
    };
    //          function: get sign in user response 처리 함수 //
    const getSignInUserResponse = (responseBody: GetSignInUserResponseDto | ResponseDto) => {
      const { code } = responseBody;
      if (code !== 'SU') {
        setCookie('accessToken', '', { expires: new Date(), path: MAIN_PATH });
        setUser(null);
        return;
      }

      setUser({ ...responseBody as GetSignInUserResponseDto });
    };

    //          event handler: 프로필 이미지 클릭 이벤트 처리          //
    const onProfileImageClickHandler = () => {
      if (!isMyPage) return;
      if (!fileInputRef.current) return;
      fileInputRef.current.click();
    };
    //          event handler: 닉네임 변경 버튼 클릭 이벤트 처리          //
    const onChangeNicknameButtonClickHandler = () => {
      if (!showChangeNickname) {
        setShowChangeNickname(true);
        return;
      }

      const isEqual = nickname === existingNickname;
      if (isEqual) {
        setShowChangeNickname(false);
        return;
      }

      const accessToken = cookies.accessToken;
      if (!accessToken) return;

      const requestBody: PatchNicknameRequestDto = { nickname };
      patchNicknameRequest(requestBody, accessToken).then(patchNicknameResponse);
    }

    //          event handler: 프로필 이미��  ���벤트 처리          //
    const onProfileImageChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      if (!event.target.files || !event.target.files.length) return;

      const file = event.target.files[0];
      const data = new FormData();
      data.append('file', file);

      fileUploadRequest(data).then(fileUploadResponse);
    };
    //          event handler: 닉네임 변경 이벤트 리          //
    const onNicknameChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const nickname = event.target.value;
      setNickname(nickname);
    };

    //          effect: 조회하는 유저의 이메일이 변경될 때 마다 실행할 함수          //
    useEffect(() => {
      if (!searchEmail) {
        navigator(MAIN_PATH);
        return;
      }
      getUserRequest(searchEmail).then(getUserResponse);
    }, [searchEmail]);

    //          render: 유저 정보 컴포넌트 렌더링          //
    return (
      <div id='user-info-wrapper'>
        <div className='user-info-container'>
          <div className={isMyPage ? 'user-info-profile-image-box-mypage' : 'user-info-profile-image-box'} onClick={onProfileImageClickHandler}>
            <input ref={fileInputRef} type='file' accept='image/*' style={{ display: 'none' }} onChange={onProfileImageChangeHandler} />
            {profileImage === null ? (
            <div className='user-info-profile-default-image'>
              <div className='user-info-profile-icon-box'>
                <div className='image-box-white-icon'></div>
              </div>
            </div>
            ) : (
            <div className='user-info-profile-image' style={{ backgroundImage: `url(${profileImage})` }}></div>
            )}
          </div>
          <div className='user-info-meta-box'>
            <div className='user-info-nickname-box'>
              {showChangeNickname ? (
              <input className='user-info-nickname-input' type='text' size={nickname.length + 1} value={nickname} onChange={onNicknameChangeHandler} />
              ) : (
              <div className='user-info-nickname'>{nickname}</div>
              )}
              {isMyPage && (
              <div className='icon-button' onClick={onChangeNicknameButtonClickHandler}>
                <div className='edit-light-icon'></div>
              </div>
              )}
            </div>
            <div className='user-info-email'>{email}</div>
          </div>
        </div>
      </div>
    );

  };

  //          component: 유저 게시물 컴포넌트          //
  const UserBoardList = () => {

    //          state: 페이네이션 관련 상태          //
    const { currentPageNumber, setCurrentPageNumber, currentSectionNumber, setCurrentSectionNumber,
    viewBoardList, viewPageNumberList, totalSection, setBoardList } = usePagination<BoardListItem>(5);
    //          state: 게시물 개수 상태          //
    const [count, setCount] = useState<number>(0);

    //          function: get user board list response 처리 함수          //
    const getUserBoardListResponse = (responseBody: GetUserBoardListResponseDto | ResponseDto) => {
      const { code } = responseBody;
      if (code === 'NU') alert('존재하지 않는 유저입니다.');
      if (code === 'DBE') alert('데이터베이스 오류입니다.');
      if (code !== 'SU') {
        navigator(MAIN_PATH);
        return;
      }

      const { userBoardList } = responseBody as GetUserBoardListResponseDto;
      setBoardList(userBoardList);
      setCount(userBoardList.length);
    }

    //          event handler: 버튼 클릭 이벤트 처리          //
    const onButtonClickHandler = () => {
      if (!user) {
        alert('로그인이 필요합니다.');
        navigator(AUTH_PATH);
        return;
      }

      if (isMyPage) navigator(BOARD_WRITE_PATH);
      else navigator(USER_PATH(user.email));
    }

    //          effect: 조회하는 유저의 이메일이 변경될 때 마다 실행할 ��수 //
    useEffect(() => {
      if (!searchEmail) {
        navigator(MAIN_PATH);
        return;
      }
      getUserBoardListRequest(searchEmail).then(getUserBoardListResponse);
    }, [searchEmail]);

    //          render: 유저 게시물 컴포넌트 렌더링          //
    return (
      <div id='user-board-wrapper'>
        <div className='user-board-container'>
          <div className='user-board-title-box'>
            <div className='user-board-title'>{'내 개시물 '}<span className='emphasis'>{count}</span></div>
          </div>
          <div className='user-board-contents-box'>
            {count === 0 ? (
            <div className='user-board-contents-nothing'>{'게시물이 없습니다.'}</div>
            ) : (
            <div className='user-board-contents-result-box'>
              {viewBoardList.map(boardItem => <BoardItem boardItem={boardItem} />)}
            </div>
            )}
            <div className='user-board-button-box' onClick={onButtonClickHandler}>
              <div className='user-board-button-contents'>
                {isMyPage ? (
                <>
                <div className='icon-box'>
                  <div className='edit-light-icon'></div>
                </div>
                <div className='user-board-button-text'>{'글쓰기'}</div>
                </>
                ) : (
                <>
                <div className='user-board-button-text'>{'내 게시물로 가기'}</div>
                <div className='icon-box'>
                  <div className='arrow-right-icon'></div>
                </div>
                </>
                )}
              </div>
            </div>
          </div>
          <div className='user-board-pagination-box'>
            {count !== 0 && (
            <Pagination 
              currentPageNumber={currentPageNumber}
              currentSectionNumber={currentSectionNumber}
              setCurrentPageNumber={setCurrentPageNumber}
              setCurrentSectionNumber={setCurrentSectionNumber}
              viewPageNumberList={viewPageNumberList}
              totalSection={totalSection}
            />
            )}
          </div>
        </div>
      </div>
    );
  };

  //          effect: 조회하는 유저의 이메일이 변경될 때 마다 실행할 함수 //
  useEffect(() => {
    const isMyPage = searchEmail === user?.email;
    setMyPage(isMyPage);
  } , [searchEmail, user]);

  //          render: 유저 페이지 렌더링          //
  return (
    <div className="user-page-container">
      <UserInfo />
      <div className="calendar-section">
        <div className="calendar-wrapper">
          <h2 className="calendar-title">운동 일지</h2>
          <div className="calendar-content-container">
            <div className="calendar-container">
              <Calendar
                onChange={handleDateClick}
                value={selectedDate}
                tileContent={tileContent}
                locale="ko"
                formatDay={(locale, date) => date.getDate().toString()}
              />
            </div>
            <div className="recent-entries-container">
              <h3>최근 운동 기록</h3>
              <div className="recent-entries-list">
                {Object.entries(diaryEntries)
                  .sort((a, b) => new Date(b[0]).getTime() - new Date(a[0]).getTime())
                  .slice(0, 5)
                  .map(([date, content]) => {
                    // 날짜 포맷팅을 위한 함수
                    const formatDate = (dateString: string) => {
                      const date = new Date(dateString);
                      const year = date.getFullYear();
                      const month = date.getMonth() + 1; // 월에 1을 더해줍니다
                      const day = date.getDate() + 1;
                      return `${year}년 ${month}월 ${day}일`;
                    };

                    return (
                      <div key={date} className="recent-entry-item">
                        <div className="recent-entry-date">
                          {formatDate(date)}
                        </div>
                        <div className="recent-entry-content">
                          {content.length > 100 ? content.substring(0, 100) + '...' : content}
                        </div>
                      </div>
                    );
                  })}
                {Object.keys(diaryEntries).length === 0 && (
                  <div className="no-entries-message">
                    아직 작성된 운동 기록이 없습니다.
                  </div>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
      {showDiaryModal && (
        <>
          <div className="modal-overlay" onClick={handleCloseModal} />
          <div className="diary-modal">
            <h3>{selectedDate?.toLocaleDateString()} 운동 기록</h3>
            <textarea
              value={diaryContent}
              onChange={(e) => setDiaryContent(e.target.value)}
              placeholder="오늘의 운동을 기록해보세요...
              
예시:
- 스쿼트 3세트 (12회)
- 데드리프트 4세트 (10회)
- 러닝 30분"
            />
            <div className="diary-modal-buttons">
              <button onClick={handleCloseModal}>취소</button>
              <button onClick={handleSaveDiary}>저장</button>
            </div>
          </div>
        </>
      )}
      <UserBoardList />
    </div>
  )
}
