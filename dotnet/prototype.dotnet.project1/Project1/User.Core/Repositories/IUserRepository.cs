using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using User.Core.Models.PO;

namespace User.Core.Repositories
{
    public interface IUserRepository
    {
        UserPO getUser(long userId);
    }
}
