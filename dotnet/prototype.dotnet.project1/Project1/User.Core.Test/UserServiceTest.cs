using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using User.Core.Service;
using User.Core.Services;
using User.Core.Repositories;
using Moq;
using User.Core.Models.PO;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

namespace User.Core.Test
{
    [TestClass]
    public class UserServiceTest
    {
        private Mock<IUserRepository> mockUserRepository;
        private IUserService userService;
        private IList<UserPO> userPOList;

        [TestInitialize]
        public void initialize()
        {
            userPOList = new List<UserPO>();

            for (int i = 1; i <= 10; i++)
            {
                UserPO userPO = new UserPO
                {
                    Id = i,
                    Username = "username" + i,
                    Password = i.ToString(),
                    Email = string.Format("username{0}@gmail.com.br", i)
                };

                userPOList.Add(userPO);
            }

            mockUserRepository = new Mock<IUserRepository>();

            foreach (UserPO userPO in userPOList)
            {
                mockUserRepository.Setup(x => x.getUser(userPO.Id)).Returns(userPO);
            }

            userService = new UserService(mockUserRepository.Object);
        }

        [TestMethod]
        public void GetUser_Should_ReturnObjectUserPO_When_UserIdExists()
        {
            UserPO userPO = userPOList[1];
            Assert.AreEqual(userPO, userService.GetUser(2));
        }

        [TestMethod]
        [ExpectedException(typeof(Exception))]
        public void GetUser_Should_ThrowException_When_UserIdNotExists()
        {
            userService.GetUser(456);
        }

        [TestMethod]
        public void CheckUserEmail_Should_ReturnTrue_When_EmailIsValid()
        {
            UserPO userPO = userService.GetUser(1);
            Assert.IsTrue(userService.CheckUserEmail(userPO.Email));
        }

        [TestMethod]
        public void CheckUserEmail_Should_ReturnFalse_When_EmailNotValid()
        {
            Assert.IsFalse(userService.CheckUserEmail("@d.com"));
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void CheckUserEmail_Should_ThrowArgumentException_When_ArgumentIsNull()
        {
            Assert.IsTrue(userService.CheckUserEmail(null));
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void CheckUserEmail_Should_ThrowArgumentException_When_ArgumentIsEmpty()
        {
            Assert.IsTrue(userService.CheckUserEmail(""));
        }

        [TestMethod]
        public void Login_Should_ReturnTrue_When_PasswordCorrect()
        {
            Assert.IsTrue(userService.LoginWithEmail(userPOList[0].Email, "1"));
            Assert.IsTrue(userService.LoginWithUsername(userPOList[1].Username, "2"));
        }
    }
}
