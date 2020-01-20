<template>
    <div class="wrap components-page">
        <div class="wrapB">

            <h1>Style Component</h1>

            <h2>form</h2>
            <div class="wrap">
                <!-- input type=text -->
                <InputComponent inputValue="email" :errorText="error.email"
                                :enterInput="enterInput"
                                placeholder="이메일을 입력해주세요." label="이메일"/>

                <!-- input type=password -->
                <InputComponent inputValue="password" :errorText="error.password"
                                :enterInput="enterInput"
                                placeholder="비밀번호를 입력해주세요." label="비밀번호" password="true"/>

                <!-- textarea -->
                <TextareaComponent inputValue="contents" placeholder="의견을 적어주세요." label="게시하기" maxLength=300></TextareaComponent>

                <!-- form -->
                <SelectComponent :options="options"/>
                <CheckboxComponent label="약관에 동의합니다." checkValue="agree"/>
            </div>

            <h2>switch</h2>
            <div class="wrap">
                <SwitchComponent/>
            </div>

            <h2>button</h2>
            <div class="wrap">
                <!-- button -->
                <LargeButton text="내 큐레이션에 추가" :isBackground="true"/>
                <LargeButton text="내 큐레이션에 추가" :isBackground="false"/>
                <SmallButton text="승인" :isBackground="true"/>
                <SmallButton text="거절" :isBackground="false"/>
                <HalfButton :buttons="buttons"/>
            </div>

            <h2>header</h2>
            <div class="wrap">
                <!-- header -->
                <HeaderComponent headerTitle="헤더 타이틀" :isSearch="true"/>
                <HeaderComponent headerTitle="헤더 타이틀" :isBack="true"/>
                <HeaderComponent headerTitle="헤더 타이틀" :isBack="true" rightText="저장" isDisabled="true"/>
                <HeaderComponent headerTitle="헤더 타이틀" :isBack="true" rightText="저장"/>
                <HeaderComponent headerTitle="헤더 타이틀" :isBack="true" :isSearch="true"/>
            </div>

            <h2>tab</h2>
            <div class="wrap">
                <!-- tab -->
                <TabComponent tabTitle="탭" />
                <TabComponent tabTitle="탭" :isActive="true"/>
                <TabComponent tabTitle="탭" :isActive="true" count="23"/>
            </div>

            <h2>keyword</h2>
            <div class="wrap">
                <!-- keyword -->
                <KeywordDelComponent keywordTitle="서비스" />
                <KeywordAddComponent />
            </div>

            <h2>curation</h2>
            <div class="wrap">
                <!-- curation -->
                <CurationComponent curationTitle="내 큐레이션" />
            </div>

            <h2>toast</h2>
            <div class="wrap">
                <!-- toast -->
                <ToastComponent toastTitle="스크랩되었습니다." />
                <ToastComponent toastTitle="500 Error" :isError="true"/>
                <ToastComponent toastTitle="스킵되었습니다." :isCancel="true"/>
            </div>



        </div>
    </div>
</template>

<script>
    import InputComponent from '../components/common/Input'
    import TextareaComponent from '../components/common/Textarea'
    import LargeButton from '../components/common/ButtonLarge'
    import SmallButton from '../components/common/ButtonSmall'
    import HalfButton from '../components/common/ButtonHalf'
    import SelectComponent from '../components/common/Select'
    import CheckboxComponent from '../components/common/Checkbox'
    import SwitchComponent from '../components/common/Switch'
    import HeaderComponent from '../components/common/Header'
    import TabComponent from '../components/common/Tab'
    import KeywordDelComponent from '../components/common/KeywordDel'
    import KeywordAddComponent from '../components/common/KeywordAdd'
    import CurationComponent from '../components/common/Curation'
    import ToastComponent from '../components/common/Toast'
    import '../assets/css/components.scss'

    export default {
        name: "components",
        components: {
            InputComponent,
            TextareaComponent,
            LargeButton,
            SmallButton,
            HalfButton,
            SelectComponent,
            CheckboxComponent,
            SwitchComponent,
            HeaderComponent,
            TabComponent,
            KeywordDelComponent,
            KeywordAddComponent,
            CurationComponent,
            ToastComponent,
        },
        data: () => {
            return {
                email:"",
                password:"",
                contents:"",
                error: {
                    email: "",
                    password: "",
                },
                buttons: [
                    {
                        title: '확인',
                        highlight: true,
                        onClick: () => {
                            console.log('클릭')

                        }
                    },
                    {
                        title: '취소',
                        onClick: () => {
                            console.log('클릭')
                        }
                    }
                ],
                options: [
                    {
                        value:"option1",
                        title:"옵션1",
                    },
                    {
                        value:"option2",
                        title:"옵션2",
                    },
                ]
            }
        },
        methods:{
            enterInput(e,type) {
                if(type==='email') {
                    this.email = e;
                } else if(type==="password") {
                    this.password = e;
                }
            }
        },
    }
</script>

<style scoped>

</style>