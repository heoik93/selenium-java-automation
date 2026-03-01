package com.project.constants;

public class AppMessages {

    //알림창 메세지

    //회원정보 수정페이지
    public static final String myinfoUpdateSuccessAlertMsg = "회원정보가 수정되었습니다.";

    //회원가입페이지 alert
    public static final String singUpPage_singUp_AlertMsg = "회원가입 되었습니다.";
    public static final String singUpPage_Withdraw_AlertMsg1 = " 님 탈퇴 하시겠습니까?";
    public static final String singUpPage_Withdraw_AlertMsg2 = "회원 탈퇴가 완료되었습니다.";


    //비밀번호 변경페이지
    public static final String exiPasswordAlertMsg = "기존 비밀번호가 올바르지 않습니다.";
    public static final String chagePasswordFailAlertMsg = "비밀번호를 확인 하세요!";
    public static final String chagePasswordSuccessAlertMsg = "비밀번호가 성공적으로 변경되었습니다.";

    //예약 상품안내페이지
    public static final String bookingCategoryPage_NoOption_AlertMsg = "상품을 선택해 주세요";

    //결제페이지 결제버튼클릭시 alert
    public static final String bookingPaymentPage_EmailEmpty_AlertMsg = "이메일을 입력해주세요";
    public static final String bookingPaymentPage_AddressEmpty_AlertMsg = "배송지를 입력해주세요";
    public static final String bookingPaymentPage_PaymentFailMsg = "결제 실패:";
    public static final String bookingPaymentPage_PaymentSuccessMsg = "결제가 완료되었습니다.";
    public static final String bookingPaymentPage_NoEmail_AlertMsg = "이메일 형식이 올바르지 않습니다. 예) Laundry@naver.com";

    //후기게시판 alert
    public static final String reviewDetailPage_Modify_AlertMsg = "후기가 수정되었습니다.";
    public static final String reviewDetailPage_Delect_AlertMsg = "정말 삭제하시겠습니까?";
    public static final String reviewDetailPage_Delect_Success_AlertMsg = "후기가 삭제되었습니다.";
    public static final String reviewDetailPage_Create_AlertMsg = "후기가 저장되었습니다!";

    //FAQ alert
    public static final String faqCreatePage_Create_AlertMsg_1 = "저장하시겠습니까?";
    public static final String faqCreatePage_Create_AlertMsg_2 = "저장되었습니다.";
    public static final String faqCreatePage_Create_NoCategory_AlertMsg = "카테고리를 선택해주세요.";
    public static final String faqCreatePage_Create_NoTitle_AlertMsg = "질문을 입력해주세요.";
    public static final String faqCreatePage_Create_NoContent_AlertMsg = "답변 내용을 입력해주세요.";
    public static final String faqCreatePage_Modify_AlertMsg_1 = "정말 수정하시겠습니까?";
    public static final String faqCreatePage_Modify_AlertMsg_2 = "수정되었습니다.";
    public static final String faqCreatePage_Delete_AlertMsg_1 = "정말 삭제하시겠습니까?";
    public static final String faqCreatePage_Delete_AlertMsg_2 = "삭제되었습니다.";

    //1:1문의 alert
    public static final String qnaCreatePage_Create_AlertMsg_1 = "저장하시겠습니까?";
    public static final String qnaCreatePage_Create_AlertMsg_2 = "저장되었습니다.";
    public static final String qnaDetailPage_Delete_AlertMsg_1 = "정말 이 글을 삭제하시겠습니까?";
    public static final String qnaDetailPage_Delete_AlertMsg_2 = "삭제되었습니다.";

    public static final String qnaDetailPage_AdminAnswer_Create_AlertMsg = "답변이 등록되었습니다.";
    public static final String qnaDetailPage_AdminAnswer_Modify_AlertMsg = "수정되었습니다.";
    public static final String qnaDetailPage_AdminAnswer_Delete_AlertMsg_1 = "답글을 삭제 하시겠습니까?";
    public static final String qnaDetailPage_AdminAnswer_Delete_AlertMsg_2 = "삭제되었습니다.";


    //주문상세 alert
    public static final String oderDetailPage_Refund_AlertMsg_1 = "정말 환불 요청하시겠습니까?";
    public static final String oderDetailPage_Refund_AlertMsg_2 = "환불 요청이 완료되었습니다.";

    //공지사항 alert
    public static final String noticeCreatePage_Create_AlertMsg_1 = "저장하시겠습니까?";
    public static final String noticeCreatePage_Create_AlertMsg_2 = "저장되었습니다.";
    public static final String noticeCreatePage_Modify_AlertMsg_1 = "정말 수정하시겠습니까?";
    public static final String noticeCreatePage_Modify_AlertMsg_2 = "수정되었습니다.";
    public static final String noticeCreatePage_Delete_AlertMsg_1 = "정말 삭제하시겠습니까?";
    public static final String noticeCreatePage_Delete_AlertMsg_2 = "삭제되었습니다.";

    //고객관리 alert
    public static final String adminUserInfoPage_Modify_NoSelectAlertMsg = "변경하려면 먼저 해당 항목을 체크하세요.";
    public static final String adminUserInfoPage_Modify_ChangeAdminAlertMsg = "관리자로 변경하겠습니까?";
    public static final String adminUserInfoPage_Modify_AfterChangeAlertMsg = "수정을 완료했습니다";
    public static final String adminUserInfoPage_Modify_ChangeUserAlertMsg = "고객으로 변경하겠습니까?";

    //주문관리 alert
    public static final String adminUseHistoryPage_StatusChange_AlertMsg1 = "상태를 '";
    public static final String adminUseHistoryPage_StatusChange_AlertMsg2 = "' 로 변경하시겠습니까?";
    public static final String adminUseHistoryPage_StatusChange_AlertMsg3 = "명의 회원을 수정했습니다";
    public static final String adminUseHistoryPage_StatusChange_NoCheck_AlertMsg = "수정할 회원을 선택해주세요.";
    public static final String adminUseHistoryPage_StatusChange_NoStatus_AlertMsg = "변경할 상태를 선택해주세요.";
    public static final String adminUseHistoryPage_Delivery_NoCheck_AlertMsg = "주문을 선택해주세요.";
    public static final String adminUseHistoryPage_Delivery_NoStatusGet_AlertMsg = "결제완료인 주문만 수거용 송장번호 발급이 가능합니다.";
    public static final String adminUseHistoryPage_Delivery_NoStatusSend_AlertMsg = "세탁완료인 주문만 반환용 송장번호 발급이 가능합니다.";
    public static final String adminUseHistoryPage_Delivery_OkCompany_AlertMsg1 = "택배사를 '";
    public static final String adminUseHistoryPage_Delivery_OkCompany_AlertMsg2 = "' 로 선택하시겠습니까?";
    public static final String adminUseHistoryPage_Delivery_NoMoreGet_AlertMsg = "수거용 송장번호 발급은 한번만 가능합니다.";
    public static final String adminUseHistoryPage_Delivery_NoMoreSend_AlertMsg = "반환용 송장번호 발급은 한번만 가능합니다.";








}
